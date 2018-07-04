/**
  * Created by zhaolei on 2018/6/22
  */
object Test extends App {
  System.err.println("ssssss")
  System.err.println(ETest.t3)
  System.err.println(ETest.t3)
  System.err.println(ETest.t2)
  System.err.println(ETest.t1)

  def test(t: ETest): String = t match {
    case ETest.t1 => t.toString
    case ETest.t2 => t.toString
    case ETest.t3 => t.toString
    case ETest.t4 => t.toString
  }

}
