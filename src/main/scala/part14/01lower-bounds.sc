import scala.collection.Seq

class Parent(val value: Int) {
  override def toString = s"${this.getClass.getName}($value)"
}

class Child(value: Int) extends Parent(value)

val op1: Option[Parent] = Option(new Child(1)) // ➋ Some(Child(1))
val p1: Parent = op1.getOrElse(new Parent(10)) // 结果: Child(1)
val op2: Option[Parent] = Option[Parent](null) // ➌ None
val p2a: Parent = op2.getOrElse(new Parent(10)) //结果: Parent(10)
val p2b: Parent = op2.getOrElse(new Child(100)) //结果: Child(100)
val op3: Option[Child] = Option[Child](null) // ➍ None
val p3a: Parent = op3.getOrElse(new Parent(20)) //结果: Parent(20)
val p3b: Parent = op3.getOrElse(new Child(200)) //结果: Child(200)

case class Opt[+A](value: A = null) {
  def getOrElse[B >: A](default: B): B = if (value != null) value else default
}


case class Opt1[A](value: A = null) {
  def getOrElse(default: A) = if (value != null) value else default
}


var s = Seq(12, 3, 4, 5)
s +: Seq(1)

class Upper

class Middle1 extends Upper

class Middle2 extends Middle1

class Lower extends Middle2


case class C[A >: Lower <: Upper](a: A) // 下限在上限之前出现
//case class C[A  <: Upper >: Lower](a: A) // 下限在上限之前出现