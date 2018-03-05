package akka.actor.Fault

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}

import scala.concurrent.duration._

/**
  * Created by zhaolei on 2018/3/5
  */
class Supervisor extends Actor {
  override def receive: Receive = {
    case p: Props => sender() ! context.actorOf(p)
    case msg      => context.children.foreach(_ ! msg)
  }

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy(10, 1 minute) {
    case _: ArithmeticException      => Resume
    case _: NullPointerException     => Restart
    case _: IllegalArgumentException => Stop
    case _                           => Escalate
  }
}


class Child extends Actor with ActorLogging {
  var state = 0

  override def receive: Receive = {
    case exception: Exception => throw exception
    case x: Int               => state = x
    case "get"                => log.info(s"state $state")
  }
}

object Child {
  def props = Props(new Child)
}

object SupervisorDemoApp extends App {
  private val system = ActorSystem("system")
  private val supervisor: ActorRef = system.actorOf(Props[Supervisor], "supervisor")
  private val child: Props = Child.props
  supervisor ! child

  supervisor ! 42
  supervisor ! "get"

  //  supervisor ! new ArithmeticException
  //  supervisor ! new NullPointerException
  //  supervisor ! new IllegalArgumentException
  supervisor ! new Exception("boom!")
  supervisor ! "get"


}