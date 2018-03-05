package akka.actor.lifecircl

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

/**
  * Created by zhaolei on 2018/3/2
  */
class Son1 extends Actor with ActorLogging {
  log.info(s"$getClass 1 create $getClass actor")
  private val sonson: ActorRef = context.actorOf(SonSon1.props, "sonson1")

  override def receive: Receive = {
    case msg@Restart           => throw new Exception(s"------------$getClass restart-------------")
    case msg@ToSon1Msg         => log.info(s"$getClass get msg"); sonson ! msg
    case msg@EveryChildRestart => log.info(s"$getClass EveryChildRestart")
    case Stop                  => context.stop(self)
    case _                     => log.info(s"nothing")
  }

  /** start preStart run */
  override def preStart(): Unit = {
    log.info(s"$getClass 4 -> preStart")
  }

  /** run -> postStop -> stop */
  override def postStop(): Unit = {
    log.info(s"$getClass 5 -> postStop")
  }

  /** run preRestart restart */
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    log.info(s"$getClass 6 -> pre restart reason:[$reason] message:[$message]")
    super.preRestart(reason, message)
  }

  /** restart postRestart run */
  override def postRestart(reason: Throwable): Unit = {
    log.info(s"$getClass 7 -> pre restart reason:[$reason] ")
    super.postRestart(reason)
  }

}


object Son1 {
  def props: Props = Props(new Son1())
}