package actorBingFa

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.event.LoggingAdapter


/**
  * Created by zhaolei on 2018/1/16
  */


object Example_01 extends App {

  import akka.actor.Actor
  import akka.event.Logging

  class MyActor extends Actor {
    private val log = Logging(context.system, this)

    override def receive: Receive = {
      case "test" => log.info("test")
      case _      => log.info("other")
    }
  }

  private val my_actor = ActorSystem("my_actor")
  private val log: LoggingAdapter = my_actor.log
  private val actor: ActorRef = my_actor.actorOf(Props[MyActor], name = "actor")
  log.info("send info to actor")
  actor ! "test"
  actor ! 123
  log.info("log startTime{}",my_actor.startTime)

}
