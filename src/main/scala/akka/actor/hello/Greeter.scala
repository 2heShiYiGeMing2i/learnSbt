package akka.actor.hello

import akka.actor.Actor

/**
  * Created by zhaolei on 2018/2/22
  */
object Greeter {

  case object Greet

  case object Done

}

class Greeter extends Actor {
  override def receive: Receive = {
    case Greeter.Greet =>
      println("Hello world!")
      sender() ! Greeter.Done
    case _             => println("other")

  }
}
