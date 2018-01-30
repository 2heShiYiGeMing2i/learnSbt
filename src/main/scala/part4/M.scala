package part4

/**
  * Created by zhaolei on 2018/1/26
  */
class M(val sc: StringContext) {

}

object M extends App {


  val f: Double = {
    System.err.println("aaaaa")
    1.0
  }

  def f1: Double = {
    System.err.println("aaaaa")
    1.0
  }

  for (elem <- 1 to 10) {
    M.f
  }
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  for (elem <- 1 to 10) {
    M.f1
  }
  System.err.println("sleep")
  Thread.sleep(10000)
  System.err.println("sleep")
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  System.err.println()
  for (elem <- 1 to 10) {
    M.f
  }


  //  val seq = Seq(1, 23, 3, 4, 5, 6, 7, 8)
  //  val iterator = seq.sliding(2)
  //  println(iterator.toSeq)
  //  println(iterator.toList)
  //  val ints = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)
  //  val ii = ints.sliding(3, 3)
  //  println(ii.toList)
  //  println(ii.toSeq)
  //  println(ii.toList)


  //  for {
  //    x <- Seq(1, 2, 34, 3.4, "one", "two", 'four)
  //  } {
  //    val str = x match {
  //      case 1          => "int 1"
  //      case i: Int     => s"other int $i"
  //      case d: Double  => f"double $d%.5f"
  //      case "one"      => "string one"
  //      case s: String  => s"other string $s"
  //      case unexpected => s"unexpected value $unexpected"
  //    }
  //    println(str)
  //  }

  //  val nonEmptyList = List(1, 2, 3, 4, 5)
  //  val nonEmptyVector = Vector(1, 2, 3, 4, 5)
  //  val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
  //
  //  def reverseSeqToString[T](l: Seq[T]): String = l match {
  //    case prefix :+ end => System.err.println(prefix + " ---> " + end); reverseSeqToString(prefix) + s" :+ $end"
  //    case Nil           => "Nil"
  //  }
  //
  //  for (seq <- Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq)) {
  //    println(reverseSeqToString(seq))
  //  }

  //  val nonEmptyList = List(1, 2, 3, 4, 5)
  //  val emptyList = Nil
  //  val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
  //
  //  // Process pairs
  //  def windows[T](seq: Seq[T]): String = seq match {
  //    case Seq(head1, head2, _*) => println("case 1"); s" ($head1, $head2), " + windows(seq.tail)
  //    case Seq(head, _*)         => println("case 2")
  //      s"($head, _), " + windows(seq.tail)
  //    case Nil                   => println("case 3"); "Nil"
  //  }
  //
  //  for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  //    println(windows(seq))
  //  }

}

