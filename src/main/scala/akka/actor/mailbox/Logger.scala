package akka.actor.mailbox

import akka.actor.{Actor, ActorLogging, ActorSystem, PoisonPill, Props}

/**
  * Created by zhaolei on 2018/3/6
  */
class Logger extends Actor with ActorLogging {

  self ! 'lowpriority
  self ! 'lowpriority
  self ! 'highpriority
  self ! 'pigdog
  self ! 'pigdog2
  self ! 'pigdog3
  self ! 'highpriority
  self ! PoisonPill

  override def receive: Receive = {

    case x => log.info(x.toString)
  }
}


object Logger extends App {
  val system = ActorSystem("system")
  val a = system.actorOf(Props(classOf[Logger]), "priomailboxactor")
  //  val a = system.actorOf(Props(classOf[Logger]).withDispatcher("akka.prio-dispatcher"))

}