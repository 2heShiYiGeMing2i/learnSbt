package akka.actor.message

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

/**
  * Created by zhaolei on 2018/3/3
  */
trait ProducerBehavior {
  this: Actor with ActorLogging =>
  val producerBehavior: Receive = {
    case GiveMeThings =>
      sender() ! Give("thing")
      log.info("logging")
  }
}

trait ConsumerBehavior {
  this: Actor with ActorLogging =>
  val consumerBehavior: Receive = {
    case Give(thing)     =>
      log.info("get a thing! it's[{}]", thing)
    case actor: ActorRef =>
      actor ! GiveMeThings
  }
}

class Producer extends Actor with ActorLogging with ProducerBehavior {
  override def receive: Receive = producerBehavior
}

class Consumer extends Actor with ActorLogging with ConsumerBehavior {
  override def receive: Receive = consumerBehavior
}

class ProducerAndConsumer extends Actor with ActorLogging with ProducerBehavior with ConsumerBehavior {
  override def receive: Receive = producerBehavior orElse consumerBehavior orElse producerBehavior
}

case object GiveMeThings

case class Give(thing: Any)


object ProducerTest extends App {
  private val actorSystem = ActorSystem("system")
  private val actor: ActorRef = actorSystem.actorOf(Props[ProducerAndConsumer], "actor")
  actor ! Give("Give")
  actor ! GiveMeThings
}
