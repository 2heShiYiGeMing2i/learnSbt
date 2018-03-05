package akka.actor.message

import java.util.concurrent.TimeUnit

import akka.actor.lifecircl.MyActor
import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration


/**
  * Created by zhaolei on 2018/3/2
  */
object AskDemo extends App {

  implicit val timeOut: Timeout = Timeout(new FiniteDuration(5, TimeUnit.SECONDS))

  private val actorSystem = ActorSystem("system")
  private val actorA: ActorRef = actorSystem.actorOf(MyActor.props)
  private val actorB: ActorRef = actorSystem.actorOf(MyActor.props)
  private val actorC: ActorRef = actorSystem.actorOf(MyActor.props)
  private val actorD: ActorRef = actorSystem.actorOf(MyActor.props)
  val f: Future[Result] = for {
    x ← ask(actorA, Request).mapTo[Int] // call pattern directly
    s ← (actorB ask Request).mapTo[String] // call by implicit conversion
    d ← (actorC ? Request).mapTo[Double] // call by symbolic name
  } yield Result(x, s, d)


  f pipeTo actorD // .. or ..
  pipe(f) to actorD
}

final case class Result(x: Int, s: String, d: Double)

case object Request