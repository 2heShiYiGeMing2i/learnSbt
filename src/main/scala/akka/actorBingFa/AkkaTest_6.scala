package akka.actorBingFa

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
//没有上面的这个引用 会报错误
/**
  * Created by Postbird on 2016/11/28.
  * 消息处理：?(Send-And-Receive-Future)
  */
// 消息：个人基础信息
case class BasicInfo(id: Int, name: String, age: Int)

//消息：个人兴趣信息
case class InterestInfo(id: Int, interest: String)

//消息：完整的个人信息
case class Person(basicInfo: BasicInfo, interestInfo: InterestInfo)

//基础信息对应的Actor
class BasicInfoActor extends Actor with ActorLogging {

  override def receive: PartialFunction[Any, Unit] = {
    //处理发送来的ID 然后将结果发送给sender
    case id: Int => log.info("id=" + id); sender ! BasicInfo(id, "posthird", 22)
    case _       => log.info("received unknown message!")
  }
}

//兴趣爱好对应的Actor
class InterestInfoActor extends Actor with ActorLogging {

  override def receive: PartialFunction[Any, Unit] = {
    case id: Int => log.info("id=" + id); sender ! InterestInfo(id, "篮球")
    case _       => log.info("received unknown message!")
  }
}

//Person对应的完整的Actor
class PersonActor extends Actor with ActorLogging {

  override def receive: PartialFunction[Any, Unit] = {
    case person: Person => log.info("Person :" + person)
    case _              => log.info("received unknown message")
  }
}

//处理的class
class CombineActor extends Actor {
  //seconds 需要引入concurrent.duration._
  implicit val timeout: Timeout = Timeout(5 seconds)
  val basicInfoActor: ActorRef = context.actorOf(Props[BasicInfoActor], name = "basicInfoActor")
  val interestInfoActor: ActorRef = context.actorOf(Props[InterestInfoActor], name = "interestInfoActor")
  val personActor: ActorRef = context.actorOf(Props[PersonActor], name = "personActor")

  override def receive: PartialFunction[Any, Unit] = {
    case id: Int => {
      val combineResult: Future[Person] =
        for {
          //向basicInfo 发送 send-and-receive-future 消息 mapTo方法返回将返回结果映射为BasicInfo类型
          //由于 pre 的原因 我把 < - 分开写了
          basicInfo <- ask(basicInfoActor, id).mapTo[BasicInfo]
          //向InterestInfo发送 send-and-receive-future 消息
          //三种方式
          interestInfo <- ask(interestInfoActor, id).mapTo[InterestInfo]
          interestInfo <- (interestInfoActor ask id).mapTo[InterestInfo]
          interestInfo <- (interestInfoActor ? id).mapTo[InterestInfo]
        } yield Person(basicInfo, interestInfo)
      //将Future结果发送给PersonActor
      pipe(combineResult).to(personActor)
    }
  }
}

object AkkaTest_6 extends App {
  val system = ActorSystem("send-and-receive-Future")
  val combineActor = system.actorOf(Props[CombineActor], name = "combineActor")
  combineActor ! 12345
  Thread.sleep(5000)
  //close system
  system.stop(combineActor)
}