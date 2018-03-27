import com.google.common.collect.ComparisonChain

import scala.collection.mutable

/**
  * Created by zhaolei on 2018/3/20
  */
object PredefineTest {
  def main(args: Array[String]): Unit = {
    val ints = IndexedSeq(FOO(1),
      FOO(2),
      FOO(3),
      FOO(4),
      FOO(5),
      FOO(6),
      FOO(7),
      FOO(8),
      FOO(9))
    val reverse = ints.sortBy(_.id).reverse
    reverse foreach System.err.println
  }
}

case class FOO(id: Int)
