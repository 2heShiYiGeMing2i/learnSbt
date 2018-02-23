package akka.actor.hello

import akka.actor.{Actor, Props}

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
}
