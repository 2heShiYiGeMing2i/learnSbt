package progscala2.implicits {
  package databases_api {

    case class InvalidColumnName(name: String) extends RuntimeException(s"invalid column name $name")

    trait Row {
      def getInt(colName: String): Int

      def getDouble(colName: String): Double

      def getText(colName: String): String
    }

  }

  package javadb {

    import progscala2.implicits.databases_api._

    case class JRow(representation: Map[String, Any]) extends Row {

      private def get(colName: String): Any = representation.getOrElse(colName, throw InvalidColumnName(colName))

      override def getInt(colName: String): Int = get(colName).asInstanceOf[Int]

      override def getText(colName: String): String = get(colName).asInstanceOf[String]

      override def getDouble(colName: String): Double = get(colName).asInstanceOf[Double]
    }

    object JRow {
      def apply(representation: (String, Any)*): JRow = new JRow(Map(representation: _*))
    }

  }

}
