package akka.actor.life

import akka.actor.{Actor, ActorSystem}

/**
  * Created by zhaolei on 2018/3/1
  */
trait Foo { self: Actor =>
  ???
}

class Bar extends Actor with Foo {
  ???
  def receive = {
    case x => sender ! x
  }
}

object Bar extends App{
  ActorSystem.apply("system")
}
