package akka.actor.life

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * Created by zhaolei on 2018/2/23
  */
class Kenny extends Actor {
  private val child1: ActorRef = context.actorOf(Props.empty, "child1")
  private val child2: ActorRef = context.actorOf(Props.empty, "child2")
  private val child3: ActorRef = context.actorOf(Props.empty, "child3")
  private val child4: ActorRef = context.actorOf(Props.empty, "child4")

  context.watch(child1)
  context.watch(child2)
  context.watch(child3)
  context.watch(child4)


  override def receive: Receive = {
    case ForceRestart => throw new Exception("boom!")
    case _            => System.err.println(s"actor got message $receive")
  }

  override def preStart(): Unit = {
    System.err.println("""actor "preStart" start """)
    super.preStart()
    System.err.println("""actor "preStart" end """)
  }


  override def postStop(): Unit = {
    System.err.println("""actor "postStop" start """)
    super.postStop()
    System.err.println("""actor "postStop" end """)
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    System.err.println("""actor "preRestart" start """)
    System.err.println(s"reason:$reason  message: ${message.get.toString}")
    super.preRestart(reason, message)
    System.err.println("""actor "preRestart" end """)
  }

  override def postRestart(reason: Throwable): Unit = {
    System.err.println("""actor "postRestart" start """)
    System.err.println(s"reason: $reason")
    super.postRestart(reason)
    System.err.println("""actor "postRestart" end """)
  }

}

case object ForceRestart

object LifeDemo extends App {
  private val system = ActorSystem("demo")
  private val kenny: ActorRef = system.actorOf(Props[Kenny], "kenny")

  private val letters: ActorRef = system.deadLetters

  System.err.println("send kenny string message")
  kenny ! "hello"
  Thread.sleep(1000)

  System.err.println("force restart")
  kenny ! ForceRestart
  Thread.sleep(1000)


  System.err.println("stop actor")
  system.stop(kenny)



}