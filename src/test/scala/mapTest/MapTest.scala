package mapTest

import org.scalameter.{Bench, Gen}

import scala.collection.mutable

/**
  * Created by zhaolei on 2018/6/13
  */
object MapTest extends Bench.ForkedTime {

  val ranges: Gen[Range] = for {
    size <- Gen.range("size")(10000, 50000, 10000)
  } yield 0 until size

  private val stringToString = mutable.Map.empty[String, String]

//  measure method "map" in {
//    using(ranges) curve "Range" in {
//      _.map(i => stringToString += (i.toString -> i.toString))
//    }
//  }
//  println(s"stringToString: ${stringToString.size}")

  private var stringToString1 = Map.empty[String, String]
  measure method "map" in {
    using(ranges) curve "Range" in {
      _.foreach(i => stringToString1 += (i.toString -> i.toString))
    }
  }
  println(s"stringToString1: ${stringToString1.size}")

}
