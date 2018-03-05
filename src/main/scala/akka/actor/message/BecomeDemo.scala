package akka.actor.message

import akka.actor.Actor

/**
  * Created by zhaolei on 2018/3/2
  */
class BecomeDemo extends Actor {

  import context._

  override def receive: Receive = {
    case "foo" => become(angry)
    case "bar" => become(happy)
  }

  def angry: Receive = {
    case "foo" => sender() ! "i am already angry?"
    case "bar" => become(happy)
  }

  def happy: Receive = {
    case "bar" => sender() ! "i am already happy :-)"
    case "foo" => become(angry)
  }


}



object BecomeDemo
