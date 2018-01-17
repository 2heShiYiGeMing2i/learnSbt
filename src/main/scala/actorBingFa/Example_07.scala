package actorBingFa

import akka.actor.{Actor, ActorLogging, ActorPath, ActorRef, ActorSelection, ActorSystem, Props}

/**
  * Created by zhaolei on 2018/1/16
  */
object Example_07 extends App {

  class FirstActor extends Actor with ActorLogging {
    var child: ActorRef = _


    override def preStart(): Unit = {
      child = context.actorOf(Props[MyActor], "child")
    }

    override def receive: PartialFunction[Any, Unit] = {
      case x => child ! x; log.info("first actor: {}", x)
    }
  }

  class MyActor extends Actor with ActorLogging {
    var parentActorRef: ActorRef = _

    override def preStart(): Unit = {
      //通过context.parent获取其父Actor的ActorRef
      parentActorRef = context.parent
    }

    def receive: PartialFunction[Any, Unit] = {
      case "test" => log.info("received test"); parentActorRef ! "message from ParentActorRef"
      case _      => log.info("received unknown message");
    }
  }


  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")
  //获取ActorPath
  val myActorPath = system.child("firstActor")
  //通过system.actorSelection方法获取ActorRef
  val myActor1 = system.actorSelection(myActorPath)
  systemLog.info("准备向myactor发送消息")
  //向myActor1发送消息
  //  myActor1 ! "test"
  //  myActor1 ! 123
  Thread.sleep(1000)
  //关闭ActorSystem，停止程序的运行
  systemLog.info("---------------------------")
  private val path: ActorPath = system.child("actor")
  private val actor: ActorSelection = system.actorSelection(path)
  actor ! "test"
  systemLog.info("---------------------------")

  /*actorRef 获得方法*/
  /*
    （1）通过 ActorSystem 的 actorOf 方法，不过这种方式是通过创建 Actor，然后返回其 ActorRef
    （2）通过 context.actorOf 方法，这种方式也是通过创建 Actor, 然后返回其 ActorRef
    （3）通过 context.parent、context.self、context.children 方法获取当前 Actor 的父 Actor、当前 Actor 及子 Actor 的 ActorRef，这种方式是获取已经创建好的 Actor 的 ActorRef
    （4）通过 val myActor1=system.actorSelection(myActorPath) 方法来获取 ActorRef，这种方式也是获取已经创建好的 Actor 的 ActorRef
  */

}
