package actorBingFa

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.event.LoggingAdapter

/**
  * Created by zhaolei on 2018/1/16
  */
object Example_06 extends App {

  class FirstActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case "test" => log.info("received test")
    }

    override def unhandled(message: Any): Unit = {
      log.info("message is {}", message)
    }
  }

  private val system = ActorSystem("system")
  private val log: LoggingAdapter = system.log
  private val firstActor: ActorRef = system.actorOf(Props[FirstActor], "firstActor")

  log.info("Main send to firstActor")
  firstActor ! "test"
  firstActor ! 123
  Thread.sleep(1000)
  system.stop(firstActor)

}
