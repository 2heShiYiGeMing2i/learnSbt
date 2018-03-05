package akka.actor.hello

import akka.actor.Props
import akka.actor.life.Kenny

/**
  * Created by zhaolei on 2018/2/22
  */
object Main extends App {
  akka.Main.main(Array(classOf[HelloWorld].getName))
  private val props1: Props = Props[MyActor]
  private val props2 = Props(new MyActor(null,null))
  private val props3 = Props(classOf[Kenny],"arg")



}
