package akka.actor.life

import akka.actor.{Actor, ActorRef, Props}

/**
  * Created by zhaolei on 2018/2/28
  */
object DemoActor {
  def props(magicNum: Int): Props = Props(new DemoActor(magicNum))

}

class DemoActor(magicNum: Int) extends Actor {
  override def receive: Receive = {
    case num: Int => sender() ! (num + magicNum)
  }

}

class SomeOtherActor extends Actor {

  // Props(new DemoActor(42)) would not be safe
  private val child1: ActorRef = context.actorOf(DemoActor.props(42), "demo")
  private val child2: ActorRef = context.actorOf(Props(new DemoActor(42)), "demo2") //这样不安全?????


  override def receive: Receive = {
    case _ =>
  }
}

