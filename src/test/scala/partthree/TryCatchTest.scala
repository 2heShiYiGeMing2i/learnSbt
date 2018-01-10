package partthree

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zhaolei on 2018/1/9
  */
class TryCatchTest extends FlatSpec with Matchers {
  //  val aa: List[String] = Nil
  //  val b: List[Int] = Nil
  //
  //  (aa == Nil) should be(
  //    true
  //  )
  //  (aa equals Nil) should be(
  //    true
  //  )
  //
  //  (b == Nil) should be(
  //    true
  //  )
  //  (b eq Nil) should be(
  //    true
  //  )
  //
  //  (aa == b) should be(
  //    true
  //  )
  //  (aa eq b) should be(
  //    true
  //  )

  //  val aa = List(1, 2, 3)
  //  aa.headOption should equal(Some(1))
  //  aa.tail should equal(List(2, 3))
  //  System.err.println(aa.headOption)
  //  private val list = List(1)
  //  println(list.tail)
  //  System.err.println(aa.tail.tail.tail.tail)
  //
  //  val aa = List(1, 3, 5, 7, 9)
  //  aa(0) should equal(
  //    1
  //  )
  //  aa(2) should equal(
  //    5
  //  )
  //  aa(4) should equal(
  //    9
  //  )
  //
  //  intercept[IndexOutOfBoundsException] {
  //    println(aa(5))
  //  }


  //  val aa = List(1, 3, 5, 7, 9)
  //  val b = aa.filterNot(v ⇒ v == 5) // remove where value is 5
  //
  //  aa should equal(List(1, 3, 5, 7, 9))
  //  b should equal(List(1, 3, 7, 9))

  //  var aa = List(1, 3, 5, 7, 9)
  //
  //  // get the length of the list
  //  aa.length should equal(5)
  //
  //  // reverse the list
  //  aa = aa.reverse
  //  aa should equal(
  //    List(9, 7, 5, 3, 1)
  //  )
  //
  //  // map a function to double the numbers over the list
  //  aa = aa.map { v ⇒
  //    v * 2
  //  }
  //  aa should equal(
  //    List(18, 14, 10, 6, 2)
  //  )
  //
  //  // filter any values divisible by 3 in the list
  //  aa.filter { v ⇒
  //    v % 3 == 0
  //  } should equal(
  //    List(18, 6)
  //  )

  //  val aa = List(1, 3, 5, 7)
  //  // NOTE: foldLeft uses a form called currying that we will explore later
  //  aa.foldLeft(0)(_ + _) should equal(16)
  //  aa.sum should equal(16)
  //  aa.foldLeft(10)(_ + _) should equal(26)
  //  aa.foldLeft(1)(_ * _) should equal(105)
  //  aa.product should equal(105)
  //  aa.foldLeft(0)(_ * _) should equal(0)

  val d = Nil
  val c = 3 :: d
  val b = 2 :: c
  val aa = 1 :: b

  aa should be(List(1, 2, 3))
  aa.tail should be(b)
  b.tail should be(c)
  c.tail should be(d)

}
