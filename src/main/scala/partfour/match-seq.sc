

val nonEmptySeq = Seq(1, 2, 3, 4, 5)
val emptySeq = Seq.empty[Int]
val notEmptyList = List(1, 2, 3, 4)
val emptyList = List.empty[Int] // nil 表示任意类型的空list
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val emptyVector = Vector.empty
val nonEmptyMap = Map('one -> 1, 'two -> 2, 'three -> 3)
nonEmptyMap.toSeq
val emptyMap = Map.empty[Symbol, Int] //map 不是seq的子类型

def seqToString[T](seq: Seq[T]): String = {
  seq match {
    case h +: t => s"$h +: " + seqToString(t)
    case Nil    => "nil"
  }
}
for (seq <- Seq(nonEmptySeq, emptySeq, notEmptyList,
  emptyList, nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
  println(seqToString(seq))
}

Seq(nonEmptySeq, emptySeq, notEmptyList, emptyList, nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq) foreach println
