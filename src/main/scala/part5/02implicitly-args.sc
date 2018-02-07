//封装的 将 implicity 方法 与附加类型签名 相结合 (type signature addition)

import scala.math.Ordering

case class MyList[A](list: List[A]) {
  // 接受一个类型为 ordering[b] 的隐式值做输入 调用 sortBy1 当前作用域一定存在某一个ordering 的对象实例
  def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] = list.sortBy(f)(ord)


  // 第二种实现 称为上下文定界(context bound) B:ordering 它 暗指第二个参数列表 将 接受ordering实例
  def sortBy2[B: Ordering](f: A => B): List[A] = list.sortBy(f)(implicitly[Ordering[B]])

}

val list = MyList(List(1, 3, 5, 7, 2, 4, 6, 8))
println(list.sortBy1(i => i).mkString(","))
println(list.sortBy2(i => i).mkString(","))

println(list.sortBy1(i => -i).mkString("->"))
println(list.sortBy2(i => -i).mkString("->"))

//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
//def sleep(millis: Long) = {
//  Thread.sleep(millis)
//}
//// 繁忙的处理工作；)
//def doWork(index: Int): Unit = {
//  sleep((math.random * 1000).toLong)
//}
//(1 to 5) foreach { index =>
//  val future = Future {
//    doWork(index * 10)
//  }
//
//  future.foreach {
//    case answer: Unit => println(s"Success! returned2: $answer")
//    case answer       => println(s"Success! returned1: $answer")
//    case _            => println(s"other")
//  }
//
//  future onSuccess {
//    case answer: Int => println(s"Success! returned: $answer")
//  }
//
//  future.failed.foreach {
//    _: Throwable => println
//  }
//  future onFailure {
//    case th: Throwable => println(s"FAILURE! returned: $th")
//  }
//}
//sleep(1000) // 等待足够长的时间，以确保工作线程结束。
//println("Finito!")
