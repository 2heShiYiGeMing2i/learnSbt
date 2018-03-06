package akka.actor.Fault

import akka.actor.SupervisorStrategy._
import akka.actor._
import akka.event.LoggingReceive
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._


/**
  * Created by zhaolei on 2018/3/5
  */
object FaultHandlingDocSample extends App {

}


class Listener extends Actor with ActorLogging {

  context.setReceiveTimeout(15 seconds)

  import Worker._

  override def receive: Receive = {
    case Progress(percent) =>
      log.info("current progress:{}%", percent)
      if (percent >= 100.0) {
        log.info("that all shutting down")
        //        context.system.stop(context.system)  停止系统
      }

    case ReceiveTimeout =>
      log.error("shutting down due to unavailable service")
    //        context.system.stop(context.system)  停止系统
  }

}


object Worker {

  private case object Do

  case object Start

  case object WorkStop

  case class Progress(percent: Double)

}

class Worker extends Actor with ActorLogging {

  import CounterService._
  import Worker._

  implicit val timeout: Timeout = Timeout(5 seconds)
  var progressListener: Option[ActorRef] = None
  private val counterService: ActorRef = context.actorOf(CounterService.props, "counterService")
  val totalCount = 51

  import context.dispatcher

  override def receive: Receive = LoggingReceive {
    case Start if progressListener.isEmpty =>
      progressListener = Some(sender)
      context.system.scheduler.schedule(Duration.Zero, 1 second, self, Do)
    case Do                                =>
      counterService ! Increment // 增加
      counterService ! Increment // 增加
      counterService ! Increment // 增加
      counterService ? GetCurrentCount map {
        case CurrentCount(_, count) => Progress(100.0 * count / totalCount)
      }
  }

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _: ServiceUnavailable => Stop
  }

}


object CounterService {

  case class Increment(n: Int)

  case object GetCurrentCount

  case class CurrentCount(key: String, count: Long)

  class ServiceUnavailable(msg: String) extends RuntimeException(msg)

  private case object ReConnect

  def props: Props = Props[CounterService]

}

class CounterService extends Actor with ActorLogging {

  import Counter._
  import CounterService._
  import Storage._
  import context.dispatcher

  val key: String = self.path.name
  var storage: Option[ActorRef] = None
  var counter: Option[ActorRef] = None
  var backlog: IndexedSeq[(ActorRef, Any)] = IndexedSeq.empty[(ActorRef, Any)]
  val MAX_BACK_LOG = 10000


  override def preStart(): Unit = {
    initStorage()
  }

  override def receive: Receive = LoggingReceive {
    case Entry(k, v) if k == key && counter.isEmpty         =>
      val c = context.actorOf(Counter.props(key, v))
      counter = Some(c)
      c ! UseStorage(storage)
      for ((replyto, msg) <- backlog) c tell(msg, sender = replyto)
      backlog = IndexedSeq.empty
    case msg@Increment(n)                                   => forwardOrPlaceInBacklog(msg)
    case msg@GetCurrentCount                                => forwardOrPlaceInBacklog(msg)
    case Terminated(actorRef) if storage.contains(actorRef) =>
      storage = None
      counter foreach (_ ! UseStorage(None))
      context.system.scheduler.scheduleOnce(Long.MaxValue nanos, self, ReConnect)
    case ReConnect                                          => initStorage()
  }

  def initStorage(): Unit = {
    storage = Some(context.watch(context.actorOf(Storage.ProPs, name = "storage")))
    counter foreach (_ ! UseStorage)
    storage.get ! Get(key)
  }

  def forwardOrPlaceInBacklog(msg: Any): Unit = {
    counter match {
      case Some(actorRef) => actorRef forward msg
      case None           =>
        if (backlog.size >= MAX_BACK_LOG) {
          throw new ServiceUnavailable("counter service not available,lack of initial value")
        }
        backlog :+= (sender -> msg)
    }
  }
}

object Counter {

  case class UseStorage(storage: Option[ActorRef])

  def props(key: String, v: Long): Props = Props(new Counter(key, v))

}

class Counter(key: String, initialValue: Long) extends Actor with ActorLogging {

  import Counter._
  import CounterService._
  import Storage._

  var count: Long = initialValue
  var storage: Option[ActorRef] = None


  override def receive: Receive = {
    case UseStorage(s)   =>
      storage = s
      storeCount()
    case Increment(n)    =>
      count += n
      storeCount()
    case GetCurrentCount =>
      sender() ! CurrentCount(key, count)
  }

  def storeCount(): Unit = {
    storage foreach {
      _ ! Store(Entry(key, count))
    }
  }
}


class Storage extends Actor with ActorLogging {

  import Storage._

  val db: DummyDB.type = DummyDB

  override def receive: Receive = LoggingReceive {
    case Store(Entry(key, value)) => db.save(key, value)
    case Get(key)                 => db.load(key)
  }
}

object Storage {

  case object Create

  case class Store(entry: Entry)

  case class Entry(key: String, value: Long)

  case class Get(key: String)

  class StorageException(msg: String) extends RuntimeException(msg)

  def ProPs: Props = Props(new Storage)

}

object DummyDB {

  import Storage.StorageException

  private var db = Map[String, Long]()

  @throws(classOf[StorageException])
  def save(key: String, value: Long): Unit = synchronized {
    if (value >= 11 && value <= 14)
      throw new StorageException("save fail")
    db += (key -> value)
  }

  @throws(classOf[StorageException])
  def load(key: String): Option[Long] = synchronized {
    db.get(key)
  }
}


