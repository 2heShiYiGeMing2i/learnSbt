package part15

/**
  * Created by zhaolei on 2018/3/14
  */
object test extends App {
  private var intToInt = Map.empty[Int, Int]
  intToInt += 1 -> 1
  intToInt += 2 -> 2
  intToInt += 3 -> 3
  intToInt += 4 -> 4
  intToInt += 5 -> 5
  intToInt += 6 -> 6
  intToInt += 7 -> 7
  intToInt += 8 -> 8

  private var intToInt1 = Map.empty[Int, Int]

  intToInt += 1 -> 1
  intToInt += 2 -> 2
  intToInt += 3 -> 3
  intToInt += 4 -> 4

  System.err.println(intToInt.exists(e => intToInt.getOrElse(e._1, 0) == e._2))

}
