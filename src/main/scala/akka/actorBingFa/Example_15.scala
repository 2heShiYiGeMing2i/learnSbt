package akka.actorBingFa

import akka.actor.TypedActor
import akka.actor.TypedActor.{PostStop, PreStart}
import akka.event.Logging

import scala.concurrent.{Future, Promise}

/**
  * Created by zhaolei on 2018/1/17
  */
object Example_15 extends App {

  trait MyType{
    //fire-and-forget消息
    def squareDontCare(i: Int): Unit
    //非阻塞send-request-reply消息
    def square(i: Int): Future[Int]
    //阻塞式的send-request-reply消息
    def squareNowPlease(i: Int): Option[Int]
    //阻塞式的send-request-reply消息
    def squareNow(i: Int): Int
  }

  //混入PostStop和PreStart
  class SquarerImpl(val name: String) extends  MyType with PostStop with PreStart {
    import akka.actor.TypedActor.context
    val log = Logging(context.system,TypedActor.self.getClass)
    def this() = this("SquarerImpl")

    def squareDontCare(i: Int): Unit = i * i
    def square(i: Int): Future[Int] = Promise.successful(i * i).future
    def squareNowPlease(i: Int): Option[Int] = Some(i * i)
    def squareNow(i: Int): Int = i * i

    def postStop(): Unit={
      log.info ("TypedActor Stopped")
    }
    def preStart(): Unit={
      log.info ("TypedActor  Started")
    }
  }

}
