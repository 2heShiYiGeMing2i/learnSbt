package akka.actor.cluster

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.cluster.ClusterEvent.{MemberEvent, MemberRemoved, MemberUp, UnreachableMember}
import akka.cluster.{Cluster, ClusterEvent}
import akka.event.Logging

/**
  * Created by zhaolei on 2018/3/6
  */
class SimpleClusterListener extends Actor with ActorLogging {
  private val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, ClusterEvent.initialStateAsEvents, classOf[MemberEvent], classOf[UnreachableMember])
  }


  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive: Receive = {
    case MemberUp(member)                      => log.info("member is up:[{}]", member.address)
    case UnreachableMember(member)             => log.info("member is not reachable[{}]", member)
    case MemberRemoved(member, previousStatus) => log.info("member is removed address[{}] after[{}]", member.address, previousStatus)
    case _: MemberEvent                        => log.info("ignore memberEvent")
  }
}


object ClusterTestMain extends App {
  private val system = ActorSystem("system")
  private val listener: ActorRef = system.actorOf(Props[SimpleClusterListener], "ClusterListener")

  Logging.getLogger(system, listener)

}
