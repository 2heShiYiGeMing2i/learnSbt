package akka.actor.message

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props, Stash}

/**
  * Created by zhaolei on 2018/3/3
  */
class InitDemo extends Actor with ActorLogging with Stash {
  override def receive: Receive = {
    case InitDemoMessage => log.info("demo message")
    case InitMessage     => log.info("demo init message"); context.become(init, discardOld = true)
    case msg             => log.info("source get msg[{}]", msg.toString)
  }

  def init: Receive = {
    case "done" => context.unbecome(); Thread.sleep(3000); unstashAll()
    case _      => log.info("stash"); stash()
  }


}

case object InitDemoMessage

case object InitMessage

object Sleep {
  Thread.sleep(1000)
}


object InitDemo extends App {
  private val system = ActorSystem("system")
  private val demo: ActorRef = system.actorOf(Props[InitDemo], "demo")
  demo ! "before init"
  demo ! " pre init"
  demo ! InitDemoMessage
  demo ! InitMessage
  Sleep
  demo ! "after init"
  demo ! "in inti"
  demo ! "3"
  demo ! "2"
  demo ! "1"
  demo ! "done"
  demo ! InitDemoMessage

}