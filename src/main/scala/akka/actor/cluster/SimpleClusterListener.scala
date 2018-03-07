package akka.actor.cluster

import akka.actor.{AbstractActor, ActorLogging}
import akka.cluster.ClusterEvent.{MemberEvent, UnreachableMember}
import akka.cluster.{Cluster, ClusterEvent}

/**
  * Created by zhaolei on 2018/3/6
  */
class SimpleClusterListener extends AbstractActor with ActorLogging {
    private val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self,ClusterEvent.initialStateAsEvents,classOf[MemberEvent],classOf[UnreachableMember])
  }


  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def createReceive(): AbstractActor.Receive = ???
}
