package akka.actor.DemoActor

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Timers}

import scala.concurrent.duration._

/**
  * Created by zhaolei on 2018/3/1
  */
class DemoActor(magicNum: Int) extends Actor {
  override def receive: Receive = {
    case x: Int    => Thread.sleep(500); sender() ! (x + magicNum)
    case x: String => Thread.sleep(500); sender() ! (x + magicNum)
  }
}

class SomeActor extends Actor with Timers {

  override def receive: Receive = {
    case x: String =>
      System.err.println(s"收到 $x")
    case key =>
      System.err.println(key)
      timers.startSingleTimer(key, "time", 5 seconds)
  }

}

object DemoActor {
  def props(magicNumber: Int): Props = Props(new DemoActor(magicNumber))
}

object SomeActor extends App {
  private val actorSystem = ActorSystem("system")
  private val actor: ActorRef =
    actorSystem.actorOf(Props(new SomeActor), "actor")
  //  actorSystem.actorOf(Props(new SomeActor),)

  actor ! 11;
  System.err.println("actor tell actor")
  actor ! "1"
  actor ! 22;
  System.err.println("actor tell actor")
  actor ! "1"
  actor ! 33;
  System.err.println("actor tell actor")
  actor ! "1"
  actor ! 44;
  System.err.println("actor tell actor")
  actor ! "1"
  actor ! 55;
  System.err.println("actor tell actor")
  actor ! "1"
  actor ! 66;
  System.err.println("actor tell actor")
  actor ! "1"
  //  actor ! 1
  //  actor ! "1"

}
