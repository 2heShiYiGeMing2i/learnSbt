package akka.actorBingFa

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.event.LoggingAdapter

/**
  * Created by zhaolei on 2018/1/16
  */
object Example_04 extends App {

  class FirstActor extends Actor with ActorLogging {
    private val myChild: ActorRef = context.actorOf(Props[MyActor], name = "myChild") // create an user child child actor means  myActor is firstActor son actor

    override def receive: Receive = {
      case x => myChild ! x; log.info("first -> received [{}]", x)
    }
  }

  class MyActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case "test" => log.info("received test")
      case _      => log.info("received unknown message")
    }
  }

  private val actorSystem = ActorSystem("system")
  private val log: LoggingAdapter = actorSystem.log

  private val actor: ActorRef = actorSystem.actorOf(Props(new FirstActor), "actor") // create a user child actor
  log.info("send to first actor")
  actor ! "test"
  actor ! 123
  actorSystem.stop(actor)
  Thread.sleep(5000)

}
