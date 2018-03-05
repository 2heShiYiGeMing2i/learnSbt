package akka.actor.TypeActor

import akka.actor.{Actor, ActorContext, ActorRef, ActorSystem, Props, TypedActor, TypedProps}

import scala.concurrent.Future

/**
  * Created by zhaolei on 2018/3/5
  */
class TypeActorDemo extends Actor {
  override def receive: Receive = ???
}

trait Squarer {
  def squareTell(i: Int): Unit // fire - forget

  def square(i: Int): Future[Int]

  def squareNowPlease(i: Int): Option[Int]

  def squareNow(i: Int): Int

  @throws(classOf[Exception])
  def squareTry(i: Int): Int


}

class SquarerImpl(name: String) extends Squarer {
  def this() = this("defaultL")

  override def squareTell(i: Int): Unit = i * i

  override def square(i: Int): Future[Int] = Future.successful(i * i)

  override def squareNowPlease(i: Int): Option[Int] = Some(i * i)

  override def squareNow(i: Int): Int = i * i

  override def squareTry(i: Int): Int = throw new Exception("catch me!")
}

object Demo extends App {
  private val system = ActorSystem("system")
  private val ref: ActorRef = system.actorOf(Props[TypeActorDemo])
  // 返回有类型的actor扩展
  private val extension = TypedActor(system)
  // 判断引用是不是一个类型actor
  TypedActor(system).isTypedActor(ref)
  // 返回一个外部 是typedActor 代理锁代表的akka actor
  TypedActor(system).getActorRefFor(ref)
  private val context: ActorContext = TypedActor.context
  TypedActor(TypedActor.context).typedActorOf(TypedProps[SquarerImpl]())

  private val newRef: Squarer = TypedActor(system).typedActorOf(TypedProps(classOf[Squarer],new SquarerImpl("foo")),"newRef")

}
