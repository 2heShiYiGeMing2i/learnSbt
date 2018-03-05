package akka.actor.hello

import akka.actor.{Actor, ActorContext, ActorPath, ActorRef, ActorSelection, Props}

import scala.collection.immutable

/**
  * Created by zhaolei on 2018/2/22
  */
class HelloWorld extends Actor {

  override def preStart(): Unit = {
    val helloGreeter = context.actorOf(Props[Greeter], "helloGreeter")
    helloGreeter ! Greeter.Greet
  }

  override def receive: Receive = {
    case Greeter.Done  => println("stop"); context.stop(self)
    case Greeter.Greet => println("greeter")
    case _             => println("other")
  }

  private val context1: ActorContext = this.context
  val parent: ActorRef = this.context.parent
  private val children: immutable.Iterable[ActorRef] = this.context.children


  private val child: Option[ActorRef] = this.context.child("child1")
  private val path: ActorPath = this.context.self.path
  private val path1: ActorPath = self.path

  private val selection: ActorSelection = context.actorSelection("")
  selection ! "message"

  private val ref: ActorRef = context.actorFor("")
  ref ! "message"

  private val sdfa: Option[ActorRef] = context.child("sdfa")
  private val ref1: ActorRef = sdfa.getOrElse(ref)
  ref1 ! "message"
  ref1.path


}
