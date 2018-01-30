for {
  x <- Seq(List(5.5, 5.6, 5.7), List("a", "b"))
} yield x match {
  case seqd: Seq[Double] => println("seq double", seqd)
  case seqs: Seq[String] => println("seq string", seqs)
  case _                 => ("unknown!", x)
}
//jvm 类型擦除 所以没有办法匹配到对应的类型

//解决方法 复合匹配,多重匹配

def doSeqMatch[T](seq: Seq[T]): String = {
  seq match {
    case h +: _ => h match {
      case _: Double => "Double"
      case _: String => "String"
      case _         => "other type"
    }
    case _      => "nothing"
  }
}


for {
  x <- Seq(List(5.5, 5.6, 5.7), List("a", "b"), Nil)
} yield {
  x match {
    case seq: Seq[_] => println(s"seq ${doSeqMatch(seq)}", seq)
    case _           => println("unknown!", x)
  }
}
