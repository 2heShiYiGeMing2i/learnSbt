package actorBingFa

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.event.LoggingAdapter

/**
  * Created by zhaolei on 2018/1/16
  */
object Example_05 extends App {

  class FirstActor extends Actor with ActorLogging {
    //通过context.actorOf方法创建Actor
    var child: ActorRef = _

    //Hook方法，preStart()，Actor启动之前调用，用于完成初始化工作
    override def preStart(): Unit = {
      log.info("preStart() in FirstActor")
      //通过context上下文创建Actor
      child = context.actorOf(Props[MyActor], name = "myChild")
    }

    //Hook方法，postStop()，Actor停止之后调用
    override def postStop(): Unit = {
      log.info("postStop() in FirstActor")
      context.stop(child)
    }

    override def receive: PartialFunction[Any, Unit] = {
      case x => child ! x; log.info("first received [{}]", x)
    }

  }

  //  class MyActor extends Actor with ActorLogging {
  //    //Hook方法，preStart()，Actor启动之前调用，用于完成初始化工作
  //    override def preStart(): Unit = {
  //      log.info("preStart() in MyActor")
  //    }
  //
  //    def receive: PartialFunction[Any, Unit] = {
  //      case "test" => log.info("received test")
  //      case _      => log.info("received unknown message")
  //    }
  //
  //    //Hook方法，postStop()，Actor停止之后调用
  //    override def postStop(): Unit = {
  //      log.info("postStop() in MyActor")
  //    }
  //  }

  class MyActor extends Actor with ActorLogging {


    def receive: PartialFunction[Any, Unit] = {
      case "test"                        => log.info("received test"); sender() ! "message from MyActor"
      case "message from self reference" => log.info("message from self refrence")
      case "message from MyActor"        => log.info("received unknown message"); self ! "message from self reference"
    }

  }

  private val system = ActorSystem("actorSystem")
  private val log: LoggingAdapter = system.log

  private val firstActor: ActorRef = system.actorOf(Props[FirstActor], "firstActor")
  firstActor ! "test"
  //  firstActor ! 123
  Thread.sleep(1000)
  system.stop(firstActor)

}
