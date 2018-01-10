package partthree

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zhaolei on 2018/1/9
  */
class TryCatchTest extends FlatSpec with Matchers {

  //  object Greeting {
  //    def english = "Hi"
  //
  //    def espanol = "Hola"
  //  }
  //
  //  val x = Greeting
  //  val y = x
  //  System.err.println(x eq y)
  //
  //  val z = Greeting
  //
  //  System.err.println(x eq z)
  def addWithoutSyntaxSugar(x: Int) = (x: Int, y: Int) => x + y

  //  System.err.println(addWithoutSyntaxSugar(1).isInstanceOf[(_, _) => _])

  def makeUpper(xs: List[String]) = xs map {
    _.toUpperCase
  }

  def makeWhatEverYouLike(xs: List[String], sideEffect: String ⇒ String) =
    xs map sideEffect


  makeUpper(List("abc", "xyz", "123")) should be(
    List("ABC", "XYZ", "123")
  )

  //using it inline
  val myName = (name: String) => s"My name is $name"
  makeWhatEverYouLike(List("John", "Mark"), myName) should be(List("My name is John", "My name is Mark"))


  List("Scala", "Erlang", "Clojure") map (_.length) should be(
    List(5,6,7)
  )
  makeWhatEverYouLike(List("ABC", "XYZ", "123"), { x ⇒
    x.toLowerCase
  }) should be(
    List("abc", "xyz", "123")
  )



}
