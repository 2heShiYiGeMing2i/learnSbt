package akka.actor.clusterdemo

import akka.actor.clusterdemo.DemoMessage.{DoJob, RegisterDemo, ResultDemo}
import akka.actor.{Actor, ActorSystem, Props, RootActorPath}
import akka.cluster.ClusterEvent.{CurrentClusterState, MemberEvent, MemberUp}
import akka.cluster.{Cluster, Member, MemberStatus}
import com.typesafe.config.ConfigFactory

/**
  * Created by zhaolei on 2018/3/7
  */
class ClusterServer extends Actor {

  private val cluster = Cluster(context.system)

  override def preStart(): Unit = cluster.subscribe(context.self, classOf[MemberEvent]) // 将自己挂载到集群当中

  override def postStop(): Unit = cluster.unsubscribe(self) // 将自己从集群中移除


  override def receive: Receive = {
    case DoJob(text)                => sender() ! ResultDemo(text.toUpperCase) // 返回处理结果
    case state: CurrentClusterState => state.members.filter(_.status == MemberStatus.Up) foreach register // 根据当前的节点状态像客户端注册
    case MemberUp(m)                => register(m) //根据up状态向客户端注册
  }

  def register(member: Member): Unit = {
    // 寻找actorPath 并向 actor发送注册消息
    context.actorSelection(RootActorPath(member.address) / "user" / "client") ! RegisterDemo
  }
}

object ClusterServer {
  def main(args: Array[String]): Unit = {
    val port = if (args.isEmpty) "2553" else args(0)

    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").withFallback(ConfigFactory.load("remote.conf"))
    val system = ActorSystem("ClusterSystem", config)
    system.actorOf(Props[ClusterServer], name = "server")
  }

}