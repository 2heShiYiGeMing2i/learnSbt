package progscala2.implicits {
  package scaladb {

    object implicits {

      import javadb.JRow

      implicit class SRow(jrow: JRow) {
        def get[T](colName: String)(implicit toT: (JRow, String) => T): T = toT(jrow, colName)
      }

      implicit val jrowToInt: (JRow, String) => Int = (j: JRow, colName: String) => j.getInt(colName)
      implicit val jrowToDouble: (JRow, String) => Double = (j: JRow, colName: String) => j.getDouble(colName)
      implicit val jrowToString: (JRow, String) => String = (j: JRow, colName: String) => j.getText(colName)
    }

    object DB {

      import implicits._

      def main(args: Array[String]): Unit = {
        val row = javadb.JRow("one" -> 1, "two" -> 2.2, "three" -> "3333333!", "ceshi" -> "ceshi")
        val oneValue1: Int = row.get("one")
        val twoValue1: Double = row.get("two")
        val threeValue1: String = row.get("three")
        // val fourValue1: Byte = row.get("ceshi") // won't compile
        println(s"one1   -> $oneValue1")
        println(s"two1   -> $twoValue1")
        println(s"three1 -> $threeValue1")

        val oneValue2 = row.get[Int]("one")
        val twoValue2 = row.get[Double]("two")
        val threeValue2 = row.get[String]("three")
        println(s"one2 -> $oneValue2")
        println(s"two2 -> $twoValue2")
        println(s"three2 -> $threeValue2")
      }
    }

  }

}