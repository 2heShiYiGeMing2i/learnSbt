package part3

import scala.io.Source

/**
  * Created by zhaolei on 2018/1/3
  */
object Main1 {

  def numLength(s: String): Int = s.length.toString.length

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("E:\\WorkSpace\\Z_TEST\\learnSbt\\src\\main\\scala\\partthree\\Main.scala").getLines().toList
    val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
    val max = numLength(longestLine)
    for (elem <- lines) {
      val space = max - numLength(elem)
      val str = " " * space
      println()
      System.err.println(str + elem.length + " | " + elem)
    }
  }
}

