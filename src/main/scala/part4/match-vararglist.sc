object Op extends Enumeration {
  type Op = Value

  val EQ = Value("=")
  val NE = Value("!=")
  val LT_GT = Value("<>")
  val LT = Value("<")
  val LE = Value("<=")
  val GT = Value(">=")
  val GE = Value(">=")
}

import Op._

case class WhereOp[T](colName: String, op: Op, value: T)

case class WhereIn[T](colName: String, val1: T, vals: T*)

val products = Seq(
  WhereIn("state", "IL", "CA", "VA"),
  WhereOp("state", EQ, "IL"),
  WhereOp("name", EQ, "Buck Trends"),
  WhereOp("age", GT, 29)
)
products.foreach {
  case WhereIn(col, val1, vals@_*) => // 可变参数类型的匹配用这种语法形式 name@_*
    System.err.println(s"vals is -> $vals")
    val valStr = (val1 +: vals).mkString(", ")
    println(s"WHERE $col IN ($valStr)")
  case WhereOp(col, op, value)     => println(s"WHERE $col $op $value")
  case where                       => println(s"ERROR: Unknown expression: $where")
}