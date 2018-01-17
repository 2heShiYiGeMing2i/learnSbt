package actorBingFa

import akka.actor.{ActorSystem, TypedActor, TypedProps}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}


/**
  * Created by zhaolei on 2018/1/17
  */
trait Squarer {
  //fire-and-forget消息
  def squareDontCare(i: Int): Unit

  //非阻塞send-request-reply消息
  def square(i: Int): Future[Int]

  //阻塞式的send-request-reply消息
  def squareNowPlease(i: Int): Option[Int]

  //阻塞式的send-request-reply消息
  def squareNow(i: Int): Int

  class SquarerImpl(val name: String) extends Squarer {
    def this() = this("SquarerImpl")

    override def squareDontCare(i: Int): Unit = System.err.println("squareDontCare")

    override def square(i: Int): Future[Int] = Promise.successful(i * i).future

    override def squareNowPlease(i: Int): Option[Int] = Some(i * i)

    override def squareNow(i: Int): Int = i * i
  }

  /*
  trait Squarer 中定义了 4 个方法：
  （1）def squareDontCare(i: Int): Unit 方法：返回值类型为 Unit，它类似于 Untyped Actor 中的 fire-and-forget 消息发送模型，即！和 tell 方法调用。
  （2）def square(i: Int): Future[Int]：返回值类型为 Future[Int]，它类似于 Untyped Actor 中的 send-request-reply 消息发送模型，即? 和 ask 方法调用，此种调用是非阻塞的。
  （3）def squareNowPlease(i: Int): Option[Int]：返回值类型为 Option[Int]（Option 类可以是 scala.Option[_] 也可以是 akka.japi.Option
  */

  private val system = ActorSystem("system")
  //  private val actor: ActorRef = system.actorOf(Props[MyActor], "actor")

  //直接通过默认的构造函数创建Typed Actor
  val mySquarer: Squarer = TypedActor(system).typedActorOf(TypedProps[SquarerImpl]())
  //直接通过默认的构造函数创建Typed Actor并指定Typed Actor名称
  val mySquarer1: Squarer = TypedActor(system).typedActorOf(TypedProps[SquarerImpl](), "mySquarer")
  //通过非默认的构造函数创建Typed Actor并指定Typed Actor名称
  val otherSquarer: Squarer = TypedActor(system).typedActorOf(TypedProps(classOf[Squarer], new SquarerImpl("SquarerImpl")), "otherSquarer")

  mySquarer.squareDontCare(10)

  private val request_reply: Option[Int] = mySquarer1.squareNowPlease(10)
  private val request_reply_1: Int = mySquarer1.squareNow(10)
  val fSquare: Future[Int] = mySquarer.square(10)
  val result: Int = Await.result(fSquare, 5 second)
}

