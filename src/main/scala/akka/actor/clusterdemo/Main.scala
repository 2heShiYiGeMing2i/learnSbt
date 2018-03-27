package clusterdemo

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.clusterdemo.{ClusterServer, DemoClusterClient, SendSend, SendingActor}
import akka.actor.{ActorSystem, Props}

import scala.concurrent.duration._
import scala.io.StdIn

/**
  * Created by zhaolei on 2018/3/7
  */
object Main {

  def main(args: Array[String]): Unit = {

    System.err.println("start main")
    ClusterServer.main(Seq("3551").toArray) // seed node 最先起服
    System.err.println("start main down")
    Thread.sleep(20000)
    System.err.println("start other")
    ClusterServer.main(Seq("8002").toArray)
    ClusterServer.main(Seq("8003").toArray)

    DemoClusterClient.main(Seq("2551").toArray) //连接远端seed

    val system = ActorSystem("OTHERSYSTEM")
    val actorRef = system.actorOf(Props[SendingActor], name = "actorRefDemo")
    val counter = new AtomicInteger
    import system.dispatcher
    system.scheduler.schedule(2.seconds, 2.seconds) {
      actorRef ! SendSend(counter.incrementAndGet())
    }
    StdIn.readLine()
    system.terminate()
  }

}
