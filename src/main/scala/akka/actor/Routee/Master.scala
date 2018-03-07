package akka.actor.Routee

import akka.actor.{Actor, Props, Terminated}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}

/**
  * Created by zhaolei on 2018/3/6
  */
class Master extends Actor {

  var router: Router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(Worker.props())
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees) //轮询
  }

  override def receive: Receive = {
    case w: Work       => router.route(w, sender = sender())
    case Terminated(a) =>
  }


}

class Worker extends Actor {
  override def receive: Receive = ???
}

object Worker {
  def props(): Props = Props(new Worker)

}

case class Work()


