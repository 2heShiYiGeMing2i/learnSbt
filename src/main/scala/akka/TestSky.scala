package akka

import org.scalameter.api._

/**
  * Created by wait on 2018/3/24.
  */
object TestSky extends Bench.ForkedTime {

  val ranges = for {
    size <- Gen.range("size")(1000000000, 2000000000, 100000000)
  } yield 0 until size

  performance of "Range" in {
    measure method "map" in {
      using(ranges) in { r =>
        T1.foo2(r.max)
      }
    }
  }

}

object T1 {
  @inline
  def foo1(i: Int): Int = {
    if (i % 3 == 1) i + 3 else 5 + i
  }

  @noinline
  def foo2(i: Int): Int = if (i % 3 == 1) i + 3 else 5 + i
}
