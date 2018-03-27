package akka.actor.clusterdemo

import akka.actor.clusterdemo.DemoMessage.{DoJob, ResultDemo}
import akka.actor.{Actor, ActorPath, ActorRef}
import akka.cluster.client.{ClusterClient, ClusterClientSettings}
import akka.pattern.Patterns
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success}

/**
  * Created by zhaolei on 2018/3/7
  */
class SendingActor extends Actor {

  val initialContacts = Set(ActorPath.fromString("akka.tcp://ClusterSystem@127.0.0.1:2551/system/receptionist"))
  val setting: ClusterClientSettings = ClusterClientSettings(context.system).withInitialContacts(initialContacts)

  private val actorRef: ActorRef = context.system.actorOf(ClusterClient.props(setting), "demo-client")
  val tt = 0

  override def receive: Receive = {
    case ResultDemo(result) => System.err.println(s"Client response and the result is $result")
    case SendSend(i)            =>
      val job = DoJob(s"hello - $i")
      implicit val timeout: Timeout = Timeout(3 seconds)
      val result = Patterns.ask(actorRef, ClusterClient.Send("/user/client", job, localAffinity = true), timeout)
      result.onComplete {
        case Success(r) => self ! r
        case Failure(f) => System.err.println(s"an error has occure ${f.getMessage}")
      }
  }
}

case class SendSend(count: Int)
