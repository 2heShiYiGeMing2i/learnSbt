val nonEmptySeq = Seq(1, 2, 3, 4, 5)
val emptySeq = Seq.empty[Int]
val nonEmptyMap = Map('one -> 1, 'two -> 2, 'three -> 3)


def seqToString[T](seq: Seq[T]): String = {
  seq match {
    case h +: t => s"($h +: ${seqToString(t)})"
    case Nil    => "nil"
  }
}

def seqToString1[T](seq: Seq[T]): String = {
  seq match {
    case h :: t => s"($h :: ${seqToString1(t)})"
    case Nil    => "nil"
  }
}

Seq(nonEmptyMap.toSeq, nonEmptySeq, emptySeq)
  .foreach(seq => println(seqToString(seq)))
Seq(nonEmptySeq, emptySeq)
  .foreach(seq => println(seqToString1(seq)))

val s2 = ("one", 1) +: (("two", 2) +: (("three", 3) +: Nil))
