package akka.actor.Fault

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy, SupervisorStrategy}

import scala.concurrent.duration._


/**
  * Created by zhaolei on 2018/3/5
  */
class FaultDemo extends Actor {
  override def receive: Receive = ???

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy(10, 1 minute /*Duration.Inf*/) {
    // 重启十次(负值没有时间限制) 持续时长(Duration.Inf表示没有时间限制)
    case _: ArithmeticException      => Resume
    case _: NullPointerException     => Restart
    case _: IllegalArgumentException => Stop
    case t                           => super.supervisorStrategy.decider.applyOrElse(t, (_: Any) => Escalate) // 默认容错处理
  }


}

object M extends App {
  System.err.println(Long.MaxValue)
  System.err.println(Int.MaxValue)
  System.err.println(Long.MaxValue / Int.MaxValue)
  val value = 1000000000L
  System.err.println((value nanos).toString())
  System.err.println((value nanos).toMicros.toString)
  System.err.println((value nanos).toMillis.toString)
  System.err.println((value nanos).toSeconds.toString)
}
