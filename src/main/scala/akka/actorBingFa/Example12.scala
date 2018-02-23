package akka.actorBingFa

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

/**
  * Created by zhaolei on 2018/1/17
  * 消息处理：!(Fire-and-Forget)
  * 重写 tell 方法?
  */
object Example12 extends App {

  case class Start(var msg: String)

  case class Run(var msg: String)

  case class Stop()

  final case class Result(x: Int)

  case object Request

  class ExampleActor extends Actor with ActorLogging {
    private val other: ActorRef = context.actorOf(Props[OtherActor], "otherActor")

    override def receive: Receive = {
      case Start(msg) => other ! msg //使用fire-and-forget消息模型向OtherActor发送消息，隐式地传递sender
      case Run(msg)   => other.tell(msg, sender) /*使用fire-and-forget消息模型向OtherActor发送消息，直接调用tell方法，显式指定sender*/
      case x          => log.info("noSender"); other.tell(x, Actor.noSender)
    }
  }

  class OtherActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case s: String => log.info("other actor: [{}]", s)
      case _         => log.info("other actor unknown message")
    }
  }

  val system = ActorSystem("MessageProcessingSystem")


  val exampleActor = system.actorOf(Props[ExampleActor], name = "ExampleActor")

  exampleActor ! Run("Running")
  exampleActor ! Start("Start")
  exampleActor ! Stop()

}
