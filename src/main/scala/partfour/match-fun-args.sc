case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int)

val as = Seq(
  Address("1 scala", "zl_1", "usa"),
  Address("2 java", "zl_2", "usa")
)

val ps = Seq(
  Person("zl1", 29),
  Person("zl2", 30),
  Person("zl3", 31)
)

val pas = ps zip as // 序列压缩 组合成元组 seq

// pas: Seq[(Person, Address)] = List((Person(zl1,29),Address(1 scala,zl_1,usa)), (Person(zl2,30),Address(2 java,zl_2,usa)))


var strings = pas map {
  tup => {
    val Person(name, age) = tup._1
    val Address(street, city, country) = tup._2
    s"$name (age: $age) live at $street $city in$country"
  }
}
strings.foreach(println)

strings = pas map {
  case (Person(name, age), Address(street, city, country)) => s"$name (age: $age) live at $street $city in$country"
}
strings.foreach(println)


pas map {
  case (Person(name, age), Address(street, city, country)) => s"$name (age: $age) live at $street $city in$country"
}

