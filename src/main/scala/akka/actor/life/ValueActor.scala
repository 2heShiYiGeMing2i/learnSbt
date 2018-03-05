package akka.actor.life

import akka.actor.{Actor, ActorRef, ActorSystem, DeadLetter, Inbox, Props, Terminated}

/**
  * Created by zhaolei on 2018/3/1
  */

case class MyValueClass(v: Int) extends AnyVal

object ValueActor extends App {
  //  val valueClassProp = Props(classOf[ValueActor],MyValueClass(5)) // Unsupported
  //  val defaultValueProp1 = Props(classOf[DefaultValueActor], 2.0) // Unsupported
  //  val defaultValueProp2: Props = Props[DefaultValueActor2] // Unsupported
  //    val defaultValueProp3 = Props(classOf[DefaultValueActor2]) // Unsupported
  //  private val defaultValueProp3 = Props(new DefaultValueActor2(2)) // Unsupported
  //  private val props = Props(new ValueActor(MyValueClass(4)))
  //
  private val system = ActorSystem.apply("system")
  implicit val inbox: Inbox = Inbox.create(system)
  private val ref1: ActorRef = system.actorOf(MyFactory.myProps(1), "ref1")
  private val deadLetterActor: ActorRef = system.actorOf(Props[MyDeadLetter], "deadLetterActor")
  System.err.println(ref1)
  ref1 ! "kill"

  //  system / "system"
  System.err.println(system.deadLetters)
  Thread.sleep(1000)
  inbox.watch(system.deadLetters)
  //  inbox watch ref1

  //
  //
  //  private val ref3: ActorRef = system.actorOf(props)
  //  ref3 ! 2
  //
  //  private val ref2: ActorRef = system.actorOf(defaultValueProp3)
  //  ref2 ! 1

  //  private val son: ActorRef = system.actorOf(valueClassProp)
  //  son ! 1


}

class ValueActor(value: MyValueClass) extends Actor {

  private val child: ActorRef = context.actorOf(Props.empty, "child")
  private val ref: ActorRef = context.watch(child)
  var lastSender: ActorRef = context.system.deadLetters


  override def receive: Receive = {
    case multiplier: Int ⇒
      System.err.println(s"system= ${context.system}")
      sender() ! (value.v * multiplier)
    case "kill"          => context.stop(child)
      lastSender = sender()

    case "restart"           => System.err.println("_____________________restart_____________________")
    case Terminated(`child`) => lastSender ! "finished"; System.err.println("lastSender: " + lastSender)


  }
}

class DefaultValueActor(a: Int, b: Int = 5) extends Actor {
  def receive = {
    case x: Int ⇒ sender() ! ((a + x) * b)
  }
}


class DefaultValueActor2(b: Int = 5) extends Actor {
  val selfSender: ActorRef = sender()
  private val child: ActorRef = context.actorOf(Props.empty, "child")
  private val ref: ActorRef = context.watch(child)
  var lastSender = context.system.deadLetters

  def receive = {

    case x: Int ⇒
      System.err.println(selfSender)
      selfSender ! (x * b)
  }
}

class MyDeadLetter extends Actor {
  override def receive: Receive = {
    case letter: DeadLetter => System.err.println(s"sender: $sender  msg: $letter")
  }
}

object MyFactory {
  def myProps(i: Int): Props = {
    //    Props(new ValueActor(MyValueClass(5)))
    Props(classOf[ValueActor], MyValueClass(5).v)

  }

  def myProps1(i: Int): Props = {
    Props(classOf[DefaultValueActor], 2.0)
  }
}


class Argument(val value: String) extends AnyVal

class ValueClassActor(arg: Argument) extends Actor {
  def receive = {
    case _ ⇒ ()
  }
}

object ValueClassActor {
  def props1(arg: Argument) = Props(classOf[ValueClassActor], arg) // fails at runtime
  def props2(arg: Argument) = Props(classOf[ValueClassActor], arg.value) // ok
  def props3(arg: Argument) = Props(new ValueClassActor(arg)) // ok
}