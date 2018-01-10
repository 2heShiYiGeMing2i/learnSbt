package partthree

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zhaolei on 2018/1/9
  */
class TryCatchTest extends FlatSpec with Matchers {

  object Greeting {
    def english = "Hi"

    def espanol = "Hola"
  }

  val x = Greeting
  val y = x
  System.err.println(x eq y)

  val z = Greeting

  System.err.println(x eq z)
}
