package akka.actorBingFa

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._


/**
  * Created by zhaolei on 2018/1/17
  * 消息处理：?(Send-And-Receive-Future)
  * 重写 ask 方法
  */

object Example13 extends App {

  //消息：个人基础信息
  case class BasicInfo(id: Int, name: String, age: Int)

  //消息：个人兴趣信息
  case class InterestInfo(id: Int, interest: String)

  //消息: 完整个人信息
  case class Person(basicInfo: BasicInfo, interestInfo: InterestInfo)

  class BasicInfoActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case id: Int ⇒ log.info("id=" + id); sender ! BasicInfo(id, "John", 19) //处理送而来的用户ID，然后将结果发送给sender（本例中对应CombineActor）
      case _       ⇒ log.info("BasicInfoActor unknown message")
    }
  }

  class InterestInfoActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case id: Int ⇒ log.info("id=" + id); sender ! InterestInfo(id, "足球") //处理发送而来的用户ID，然后将结果发送给sender（本例中对应CombineActor）
      case _       ⇒ log.info("InterestInfoActor unknown message")
    }
  }

  class PersonActor extends Actor with ActorLogging {
    override def receive: Receive = {
      case person: Person => log.info("person -> [{}]", person)
      case _              ⇒ log.info("PersonActor unknown message")
    }
  }

  class CombineActor extends Actor with ActorLogging {
    implicit val timeout: Timeout = Timeout(5 seconds)
    val basicInfoActor: ActorRef = context.actorOf(Props[BasicInfoActor], name = "basicInfoActor")
    val interestInfoActor: ActorRef = context.actorOf(Props[InterestInfoActor], name = "interestInfoActor")
    val personActor: ActorRef = context.actorOf(Props[PersonActor], name = "personActor")


    override def receive: PartialFunction[Any, Unit] = {
      case id: Int => {
        val combineResult: Future[Person] =
          for {
            //向basicInfo 发送 send-and-receive-future 消息 mapTo方法返回将返回结果映射为BasicInfo类型
            basicInfo <- ask(basicInfoActor, id).mapTo[BasicInfo]
            interestInfo <- ask(interestInfoActor, id).mapTo[InterestInfo] //向InterestInfo发送 send-and-receive-future 消息
//            interestInfo <- (interestInfoActor ask id).mapTo[InterestInfo]
//            interestInfo <- (interestInfoActor ? id).mapTo[InterestInfo]
            //三种方式
          } yield {
            log.info("ask for yield")
            Person(basicInfo, interestInfo)
          }
        //将Future结果发送给PersonActor
        log.info("[{}]", combineResult.value)
        pipe(combineResult).to(personActor)
      }
    }
  }


  val _system = ActorSystem("Send-And-Receive-Future")
  val combineActor = _system.actorOf(Props[CombineActor], name = "CombineActor")
  combineActor ! 12345
  Thread.sleep(2000)
}
