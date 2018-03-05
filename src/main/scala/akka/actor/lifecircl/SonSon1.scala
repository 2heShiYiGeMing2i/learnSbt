package akka.actor.lifecircl

import akka.actor.{Actor, ActorLogging, Props}

/**
  * Created by zhaolei on 2018/3/2
  */
class SonSon1 extends Actor with ActorLogging {
  override def receive: Receive = {
    case msg => log.info(s"get message $msg sender [$sender]")
  }

  override def preStart(): Unit = {
    log.info(s"sonson1 pre start payh is:[${self.path}]")
  }

  override def postStop(): Unit = {
    log.info(s"sonson1 post stop")
  }

}

object SonSon1 {
  def props: Props = Props(new SonSon1)
}
