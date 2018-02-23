package akka.actorBingFa

import akka.actor.{Actor, ActorLogging, ActorPath, ActorRef, ActorSelection, ActorSystem, Props}
import akka.event.LoggingAdapter

/**
  * Created by zhaolei on 2018/1/16
  */
object Example_09 extends App {

  class FirstActor extends Actor with ActorLogging {
    var child: ActorRef = _

    override def preStart(): Unit = {
      child = context.actorOf(Props[MyActor], name = "myActor")
    }

    override def receive: Receive = {
      case x => child ! x; log.info("first received {}", x)
    }

  }

  class MyActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case "test" => log.info("received test");
      case _      => log.info("received unknown message");
    }
  }


  val system = ActorSystem("MyActorSystem")
  val systemLog: LoggingAdapter = system.log
  val firstActor: ActorRef = system.actorOf(Props[FirstActor], name = "firstActor")
  private val path: ActorPath = system.child("firstActor")
  private val actorPath: ActorPath = system.child("firstActor").child("myActor")
  private val selection: ActorSelection = system.actorSelection(path)

  //  selection.
  //  System.err.println()

  //  val myActor1: ActorSelection = system.actorSelection(actorPath)
  //  systemLog.info("准备向myactor发送消息")
  //  //向myActor1发送消息
  //  myActor1 ! "test"
  //  myActor1 ! 123
  //  firstactor ! "test"
  //  firstactor ! 123
  //  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
}
