package actorBingFa

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

/**
  * Created by zhaolei on 2018/1/16
  */
object Example_08 extends App {


  class FirstActor extends Actor with ActorLogging {
    var child: ActorRef = _

    override def preStart(): Unit = {
      child = context.actorOf(Props[MyActor], name = "myActor")
    }

    override def receive: Receive = {
      case x => child ! x; log.info("received {}", x)
    }
  }

  class MyActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case "test" => log.info("received test");
      case _      => log.info("received unknown message");
    }
  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

  //获取ActorPath
  val firstActorPath = system.child("firstActor")
  systemLog.info("firstActorPath--->{}", firstActorPath)
  //通过system.actorSelection方法获取ActorRef
  val myActor1 = system.actorSelection(firstActorPath)

  //直接指定其路径
  val myActor2 = system.actorSelection("/user/firstActor")
  //使用相对路径
  val myActor3 = system.actorSelection("../firstActor")

  systemLog.info("准备向myactor发送消息")
  //向myActor1发送消息
  myActor3 ! "test"
  myActor3 ! 123


}
