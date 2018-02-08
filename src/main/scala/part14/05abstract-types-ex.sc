// 抽象类型
// Scala 中抽象类型，指的是在类或 Trait 中使用 type 定义的类型


trait ExampleTrait {
  // type 定义的是类型成员
  type t1
  type t2 >: t3 <: t1
  type t3 <: t1
  //  type t3 <: t1
  type t4 <: Seq[t1]
  //  type t5 = + AnyRef  编译失败 不能使用变异标记类型  不能再类型成员上使用变异标记 这样的
  // t2 extend t1
  // t3 extend t2
  // t3 extend t1

  val v1: t1
  val v2: t2
  val v3: t3
  val v4: t4
}

trait T1 {
  val name1: String
}

trait T2 extends T1 {
  val name2: String
}

case class C(name1: String, name2: String) extends T2

object example extends ExampleTrait {
  override type t1 = T1
  override type t2 = T2
  override type t3 = C
  override type t4 = List[T1]
  override val v1 = new T1 {
    override val name1: String = "t1"
  }
  override val v2 = new T2 {
    override val name2: String = "t1"
    override val name1: String = "t2"
  }
  override val v3 = C("t1", "t2")
  override val v4 = List(C("1", "2"))
}