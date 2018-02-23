package akka.actorBingFa

import akka.actor.ActorSystem

import scala.concurrent.Await

/**
  * Created by zhaolei on 2018/1/17
  */
object Example_14 extends App {

  import akka.actor.{TypedActor, TypedProps}
  import akka.event.Logging

  import scala.concurrent.duration._
  import scala.concurrent.{Future, Promise}

  trait Squarer {
    //fire-and-forget消息
    def squareDontCare(i: Int): Unit

    //非阻塞send-request-reply消息
    def square(i: Int): Future[Int]

    //阻塞式的send-request-reply消息
    def squareNowPlease(i: Int): Option[Int]

    //阻塞式的send-request-reply消息
    def squareNow(i: Int): Int
  }

  class SquarerImpl(val name: String) extends Squarer {
    def this() = this("SquarerImpl")

    def squareDontCare(i: Int): Unit = System.err.println(s"unit is $i")

    def square(i: Int): Future[Int] = Promise.successful(i * i).future

    def squareNowPlease(i: Int): Option[Int] = Some(i * i)

    def squareNow(i: Int): Int = i * i
  }

  val system = ActorSystem("TypedActorSystem")
  val log = Logging(system, this.getClass)

  //使用默认构造函数创建Typed Actor
  val mySquarer: Squarer = TypedActor(system).typedActorOf(TypedProps[SquarerImpl](), "mySquarer")
  //使用非默认构造函数创建Typed Actor
  val otherSquarer: Squarer = TypedActor(system).typedActorOf(TypedProps(classOf[Squarer], new SquarerImpl("SquarerImpl")), "otherSquarer")


//  //fire-forget消息发送
//  mySquarer.squareDontCare(10)

//  //send-request-reply消息发送
//  val oSquare = mySquarer.squareNowPlease(10)
//
//  log.info("oSquare=" + oSquare)
//
//  val iSquare = mySquarer.squareNow(10)
//  log.info("iSquare=" + iSquare)
//
  //Request-reply-with-future 消息发送
  val fSquare = mySquarer.square(10)
  val result = Await.result(fSquare, 5 second)

  log.info("fSquare=" + result)

}
