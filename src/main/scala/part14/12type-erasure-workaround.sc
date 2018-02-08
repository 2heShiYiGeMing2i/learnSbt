object Doubler {
  def double(seq: Seq[_]): Seq[Int] = seq match {
    case Nil    => Nil
    case h +: t => (toInt(h) * 2) +: double(t)
  }

  def double1(seq: Seq[T] forSome {type T}): Seq[Int] = seq match {
    case Nil    => Nil
    case h +: t => toInt(h) +: double1(t)
  }

  def toInt(x: Any): Int = x match {
    case i: Int    => i
    case s: String => s.toInt
    case d: Double => d.toInt
    case f: Float  => f.toInt
    case _         => throw new RuntimeException(s"unexpected list element $x")
  }
}

val a1 = Array[Any](1, 2.0)

Doubler.double(a1)