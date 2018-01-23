package actorBingFa

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Stash}

/**
  * Created by zhaolei on 2018/1/18
  */
class ActorWithProtocol extends Actor with Stash {
  def receive = {
    case "open" ⇒
      System.err.println("1 open")
      //      unstashAll()
      context.become({
        case "write" ⇒
          System.err.println("2 write")
        case "close" ⇒
          System.err.println("3 close")
          //          unstashAll()
          context.unbecome()
        case msg     ⇒ System.err.println("4 other"); stash()
      }, discardOld = false)
    case "done" ⇒ System.err.println("5 done")
    case msg    ⇒ System.err.println(s"6 other $msg"); stash()
  }
}

object ActorWithProtocol extends App {
  private val system = ActorSystem("system")
  private val actor1: ActorRef = system.actorOf(Props(new ActorWithProtocol), "actor1")
  actor1.tell("open", actor1)
  actor1 ! "write"
  actor1 ! "close"
//  actor1 ! "open"
  actor1 ! "done"
  actor1 ! "done"
  actor1 ! "done"
  actor1 ! "other"
}