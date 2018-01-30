for {
  x <- Seq(1, 2, 34, 3.4, "one", "two", 'four)
} {
  val str = x match {
    case 1           => "int 1"
    case i: Int      => s"other int $i"
    case d: Double   => f"double $d%.5f"
    case "one"       => "string one"
    case s: String   => s"other string $s"
    case sym: Symbol => s"symbol $sym"
    case _           => s"unexpected value ${x.getClass.toString}"
  }
  println(str)
}

def checkY(y: Int) = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case y      => "found y!"
      case i: Int => "int: " + i
    }
    println(str)
  }
}
checkY(100)

def checkYY(y: Int) = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case `y`    => "found y!"
      case i: Int => "int: " + i
    }
    println(str)
  }
}
checkYY(100)
