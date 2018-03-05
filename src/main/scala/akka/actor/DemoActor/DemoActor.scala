package akka.actor.DemoActor

import java.io.{ByteArrayOutputStream, ObjectOutputStream}

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * Created by zhaolei on 2018/3/1
  */
class DemoActor(magicNum: Int) extends Actor {
  override def receive: Receive = {
    case x: Int    => Thread.sleep(500); sender() ! (x + magicNum)
    case x: String => Thread.sleep(500); sender() ! (x + magicNum)
  }
}

class SomeActor extends Actor {
  private val child: ActorRef = context.actorOf(DemoActor.props(1), "child")
  private val child2: ActorRef = context.actorOf(Props(new DemoActor(2)))

  import context._

  actorOf(DemoActor.props(1))
  system
  parent
  children
  unbecome()



  override def receive: Receive = {
    case x: Int    => Thread.sleep(500); child ! x; System.err.println(x)
    case x: String => Thread.sleep(500); child2 ! x; System.err.println(x)
  }

}

object DemoActor {
  def props(magicNumber: Int): Props = Props(new DemoActor(magicNumber))
}

object SomeActor extends App {
  private val actorSystem = ActorSystem("system")
  private val actor: ActorRef = actorSystem.actorOf(Props(new SomeActor), "actor")
  //  actorSystem.actorOf(Props(new SomeActor),)

  System.err.println(serialize(actor))
  //  actor ! 1
  //  actor ! "1"


  def serialize[T](o: T): Array[Byte] = {
    val stream = new ByteArrayOutputStream()
    val outputStream = new ObjectOutputStream(stream)
    outputStream.writeObject(o)
    outputStream.close()
    stream.toByteArray
  }


}



