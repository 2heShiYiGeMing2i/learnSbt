/**
  * Created by zhaolei on 2018/6/22
  */
object Test extends App {
  val set1 = Set(1, 2, 3, 4)
  val set2 = Set(1, 2, 3, 4)
  System.err.println(set1.subsetOf(set2))
}
