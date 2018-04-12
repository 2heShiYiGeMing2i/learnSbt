package akka.actor.companion

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

class ActorTest(val param: Params) extends Actor with ActorLogging {
  private val child1: ActorRef =
    context.actorOf(Props(new ChildActor(param)), "****no.0")
//  private val child: ActorRef =
//    context.actorOf(ChildActor.props(param), "****no.1")

  override def receive: Receive = {
    case Change =>
      param.name = "change"
      param.id += 2
      log.info(s"father value is: ${param.name} ${param.id}")
    case Sout => context.children.foreach(_ ! Sout)
  }
}

object ActorTest {
  def props(param: Params): Props = Props(new ActorTest(param))
}

case object Sout

case object Change

class ChildActor(val param: Params) extends Actor with ActorLogging {
  val tmpParams: Params = param
  log.info("test times")
  override def receive: Receive = {
    case Sout => log.info(s"sub value is: ${tmpParams.name} ${tmpParams.id}")
  }
}

object ChildActor {
  def props(param: Params): Props = Props(new ChildActor(param))

  //  def props(iamValue): Props = Props(new ChileActor(iamValue))
}

object ActorTestMain {
  def main(args: Array[String]): Unit = {
    var maps = Map.empty[Int,Int]
    maps += (1 -> 3)
    maps += (1 -> 3)




//    val system = ActorSystem("system")
//    val actor  = system.actorOf(ActorTest.props(new Params), "actor")
//    actor ! Sout
//    Thread.sleep(1000)
//    actor ! Change
//    Thread.sleep(1000)
//    actor ! Sout
  }
}

case class Params() {
  var name: String = "name"
  var id: Int      = 10

//  override def clone(): Params = {
//    val params = new Params
//    params.name = name
//    params.id   = id
//    params
//  }
}
