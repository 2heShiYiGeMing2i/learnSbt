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
      case _      => log.info("other"); sender() ! "test"
    }
  }

  private val actorSystem = ActorSystem("actorSystem")
  private val log: LoggingAdapter = actorSystem.log
  private val actor: ActorRef = actorSystem.actorOf(Props[MyActor], name = "actor")
  private val actor1: ActorRef = actorSystem.actorOf(Props(new MyActor), name = "actor1")

  private val MyActor1 = new MyActor
  private val actor2: ActorRef = actorSystem.actorOf(Props(MyActor1), name = "actor2") // 报错
  log.info("send info to actor")
  actor ! "test"
  actor2 ! 123
  actor1 ! "test"
  log.info("actor is same?{}", actor1.equals(actor2))

  actorSystem.stop(actor2)
  actorSystem.stop(actor1)
  actorSystem.stop(actor)

}
