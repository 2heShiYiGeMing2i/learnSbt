package akka.actor.message

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

/**
  * Created by zhaolei on 2018/3/3
  */
class Swapper extends Actor with ActorLogging {
  override def receive: Receive = {
    case Swapper =>
      log.info("hi")
      context.become({
        case Swapper =>
          log.info("ho")
          context.unbecome()
      },false)
  }
}

case object Swapper

object SwapperApp extends App {
  private val actorSystem = ActorSystem("system")
  private val swapper: ActorRef = actorSystem.actorOf(Props[Swapper], "swapper")
  swapper ! Swapper
  swapper ! Swapper
  swapper ! Swapper
  swapper ! Swapper
  swapper ! Swapper
  swapper ! Swapper
  swapper ! Swapper
  swapper ! Swapper
  swapper ! Swapper

}
