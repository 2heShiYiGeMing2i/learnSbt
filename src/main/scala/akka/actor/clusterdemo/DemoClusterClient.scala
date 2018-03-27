package akka.actor.clusterdemo

import akka.actor.clusterdemo.DemoMessage.{DoJob, JobFail, RegisterDemo, ResultDemo}
import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}
import akka.cluster.client.ClusterClientReceptionist
import akka.pattern.{ask, pipe}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Created by zhaolei on 2018/3/7
  */
class DemoClusterClient extends Actor {

  private var serverNodes: IndexedSeq[ActorRef] = IndexedSeq.empty[ActorRef]

  var jobCounter = 0

  override def receive: Receive = {
    case job: DoJob if serverNodes.isEmpty               =>
      sender() ! JobFail("job fail ", job)
    case job: DoJob                                      =>
      jobCounter += 1
      implicit val timeout: Timeout = Timeout(5 seconds)
      val backend = serverNodes(jobCounter % serverNodes.size) //根据相应算法选择执行任务的节点
      System.err.println(s"the backend is $backend and the job is $job")
      val result: Future[ResultDemo] = (backend ? job).map(x => x.asInstanceOf[ResultDemo]) // 后台节点处理得到结果
      result pipeTo sender //向外部系统发送执行结果
    case RegisterDemo if !serverNodes.contains(sender()) => // 添加新的后台任务
      context watch sender()
      serverNodes = serverNodes :+ sender()
    case Terminated(actor)                               => // 移除已经终止运行的节点
      serverNodes.filterNot(_ != actor)
  }
}

object DemoClusterClient {
  def main(args: Array[String]): Unit = {
    val port = if (args.isEmpty) "0" else args(0)
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").withFallback(ConfigFactory.load("local.conf"))
    val clusterSystem = ActorSystem("ClusterSystem", config)
    val client = clusterSystem.actorOf(Props[DemoClusterClient], name = "client")
    ClusterClientReceptionist(clusterSystem).registerService(client)


  }
}