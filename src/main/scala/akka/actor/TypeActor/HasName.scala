package akka.actor.TypeActor

import akka.actor.{ActorRef, ActorSystem, TypedActor, TypedProps}
import akka.routing.RoundRobinGroup


/**
  * Created by zhaolei on 2018/3/5
  */
trait HasName {
  def name(): String
}

class Name extends HasName {

  import scala.util.Random

  private val id: Int = Random.nextInt(1024)

  override def name(): String = s"name - $id"

}

object NameDemo extends App {
  private val system = ActorSystem("system")

  def nameActor: HasName = TypedActor(system).typedActorOf(TypedProps[Name])

  private val routees: List[HasName] = List.fill(5) {
    nameActor
  }
  private val routeePath: List[String] = routees map { r => TypedActor(system).getActorRefFor(r).path.toStringWithoutAddress }
  private val router: ActorRef = system.actorOf(RoundRobinGroup(routeePath).props())
  private val typedRouter: HasName = TypedActor(system).typedActorOf(TypedProps[Name](), actorRef = router)
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")
  System.err.println(s"actor was: ${typedRouter.name()}")

}
