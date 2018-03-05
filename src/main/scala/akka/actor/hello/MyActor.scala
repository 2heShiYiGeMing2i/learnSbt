package akka.actor.hello

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef}
import akka.pattern.ask
import akka.util.Timeout

import scala.collection.mutable
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.postfixOps

/**
  * Created by zhaolei on 2018/2/27
  */
class MyActor(echoActor: ActorRef, cleanActor: ActorRef) extends Actor {
  var state = ""
  val mySet: mutable.Set[String] = mutable.Set[String]()

  def expensiveCalculation(actorRef: ActorRef): String = {
    // this is a very costly operation
    "Meaning of life is 42"
  }

  def expensiveCalculation(): String = {
    // this is a very costly operation
    "Meaning of life is 42"
  }

  override def receive: Receive = {
    case _ =>
      implicit val ec: ExecutionContextExecutor = context.dispatcher
      implicit val timeout: Timeout = Timeout(5, TimeUnit.SECONDS) // needed for `?` below

      // Example of incorrect approach
      // Very bad: shared mutable state will cause your
      // application to break in weird ways
      Future {
        state = "This will race"
      }
      (echoActor ? Message("With this other one")).mapTo[Message].foreach { received ⇒ state = received.msg }

      // Very bad: shared mutable object allows
      // the other actor to mutate your own state,
      // or worse, you might get weird race conditions
      cleanActor ! "ssss"
      cleanActor ! mySet

      // Very bad: "sender" changes for every message,
      // shared mutable state bug
      Future {
        expensiveCalculation(sender())
      }

      // Example of correct approach
      // Completely safe: "self" is OK to close over
      // and it's an ActorRef, which is thread-safe
      Future {
        expensiveCalculation()
      } foreach {
        self ! _
      }

      // Completely safe: we close over a fixed value
      // and it's an ActorRef, which is thread-safe
      val currentSender = sender()
      Future {
        expensiveCalculation(currentSender)
      }

  }
}

class EchoActor extends Actor {
  override def receive: Receive = {
    case msg => sender() ! msg
  }
}

class CleanActor extends Actor {
  override def receive: Receive = {
    case set: mutable.Set[_] => set.clear()
  }
}


case class Message(msg: String)