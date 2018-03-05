package akka.actor.lifecircl

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

import scala.concurrent.duration.Duration

/**
  * Created by zhaolei on 2018/3/2
  */
class MyActor extends Actor with ActorLogging {
  log.info(s"$getClass 1 create $getClass actor")
  private val son1Ref: ActorRef = context.actorOf(Son1.props, "son1")
  context.actorOf(Props(new Son2), "son2")
  log.info(s"$getClass 2 --> after create actor")

  override def receive: Receive = {
    case msg@Restart           => son1Ref ! msg /*throw new Exception(s"------------$getClass  restart-------------")*/
    case msg@ToSon2Msg         => context.child("son2").get ! msg
    case msg@ToSon1Msg         => context.child("son1").get ! msg
    case msg@EveryChildRestart => context.children.foreach(_ ! msg)
    case Stop                  => context.children.foreach(context.stop)
    case StopSelf              => context.stop(self)
    case ToSonSon1             => log.info("to son son1"); context.actorSelection("son2") ! "sdsadsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    case _                     => log.info(s"$getClass my actor is receive message")
  }

  /** start preStart run */
  override def preStart(): Unit = {
    log.info(s"$getClass 4 -> preStart")
    context.setReceiveTimeout(Duration(30, TimeUnit.SECONDS))
    context.setReceiveTimeout(Duration.Undefined)
  }

  /** run -> postStop -> stop */
  override def postStop(): Unit = {
    log.info(s"$getClass 5 -> postStop")
  }

  /** run preRestart restart */
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    log.info(s"$getClass 6 -> pre restart reason:[$reason] message:[$message]")
    super.preRestart(reason, message)
  }

  /** restart postRestart run */
  override def postRestart(reason: Throwable): Unit = {
    log.info(s"$getClass 7 -> pre restart reason:[$reason] ")
    super.postRestart(reason)
  }

}

object MyActor {
  def props: Props = Props(new MyActor)
}

case object Restart

case object ToSon2Msg

case object ToSon1Msg

case object EveryChildRestart

case object Stop

case object StopSelf

case object ToSonSon1