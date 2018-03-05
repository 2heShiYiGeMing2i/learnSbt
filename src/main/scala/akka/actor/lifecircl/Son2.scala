package akka.actor.lifecircl

import akka.actor.{Actor, ActorLogging}

/**
  * Created by zhaolei on 2018/3/2
  */
class Son2 extends Actor with ActorLogging {
  log.info(s"$getClass 1 create $getClass actor")

  override def receive: Receive = {
    case msg@Restart           => throw new Exception(s"------------$getClass  restart-------------")
    case msg@ToSon2Msg         => log.info(s"$getClass get msg")
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

