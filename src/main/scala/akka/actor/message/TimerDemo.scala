package akka.actor.message

import akka.actor.{Actor, ActorRef, ActorSystem, Kill, Props, Timers}
import akka.pattern.gracefulStop

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}


/**
  * Created by zhaolei on 2018/3/2
  */
class TimerDemo extends Actor with Timers {

  import TimerDemo._

  //  timers.startPeriodicTimer(TickKey, FirstTick, 500.millis)

  override def receive: Receive = {
    case FirstTick =>
      println(s"FirstTick")
    //      timers.startPeriodicTimer('TickKey2, Tick, 100.millis)
    case Tick    =>
      System.err.println(s"tick")
    case TickKey =>
      println("TickKey")
    case "stop"  =>
      try {
        val stop: Future[Boolean] = gracefulStop(self, 5.second, Manager.Shutdown)
        Await.result(stop, 6.second)
      } catch {
        case e: akka.pattern.AskTimeoutException => System.err.println("get exception")
      }
      println(s"stop")
    case _       => self ! Kill

  }

  override def postStop(): Unit = {
    System.err.println("stop stop stop")
  }
}

object TimerDemo {

  private case object TickKey

  private case object TickKey2

  private case object FirstTick

  private case object Tick

  def props: Props = Props(new TimerDemo)

}

object Manager {

  case object Shutdown

}

object TestMain extends App {
  private val actorMain = ActorSystem("actorMain")
  private val timeDemo: ActorRef = actorMain.actorOf(TimerDemo.props)
//  timeDemo ! FirstTick
  timeDemo ! "stop"
//  timeDemo ! "stop1"
}