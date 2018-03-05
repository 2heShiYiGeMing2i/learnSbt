package akka.actor.lifecircl

import akka.actor.{ActorRef, ActorSystem}

/**
  * Created by zhaolei on 2018/3/2
  */
object Main extends App {
  private val actorSystem = ActorSystem("system")
  private val myActorRef: ActorRef = actorSystem.actorOf(MyActor.props, "myActor")
    myActorRef ! Restart
  //  Thread.sleep(1000)
  //  myActorRef ! Restart
  //  myActorRef ! EveryChildRestart
//    System.err.println("stop")
  //  myActorRef ! Stop
  //  myActorRef ! StopSelf
  //  myActorRef ! ToSon1Msg
//  Thread.sleep(3000)
//  System.err.println("select!")
//  myActorRef ! ToSonSon1
//  myActorRef ! ToSonSon1
//  myActorRef ! ToSonSon1
//  myActorRef ! ToSonSon1
//  myActorRef ! ToSonSon1
}
