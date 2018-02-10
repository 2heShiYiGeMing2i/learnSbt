import scala.math.Ordering

case class MyList[A](list: List[A]) {
  def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] = {
    // 显示定义 隐式转换参数
    list.sortBy(f)(ord)
  }

  def sortBy2[B: Ordering](f: A => B): List[A] = {
    // 隐式 定义 上下文边界
    list.sortBy(f)(implicitly[Ordering[B]])
  }
}


val list = MyList(List(1, 2, 3, 9, 8, 7))
list.sortBy1(i => i)
list.sortBy2(i => -i)

///http://www.developerq.com/article/1495465421


class C[A] {
//  def m1[B]()(implicit view: A => B): ReturnType = {
//    ???
//  }
//
//  def m2(A <% B) ():ReturnType ={
//    ???
//  }
}
