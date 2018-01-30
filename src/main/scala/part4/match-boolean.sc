val r = "[0-9]+".r
val sdd = " 123 main Street suite 101"
r.findFirstIn(sdd)
r.findAllIn(sdd).foreach(println)


//s"foo\0ar"
//raw"foo\0bar"

//Array(1, 2, 3, 4) map (_ * 8)
//val nums = List(1, 2, 3, 4, 5, 6, 7) filter (_ < 4) map (_ * 4)
//1 to 5 foreach println
//"hello world".getClass.getName
//val value = "Hello World"
//value.length
//value foreach println
//
//"scala".drop(2).take(2).capitalize
//// ==
//"scala".slice(2, 4).toUpperCase
//
//"""this is
//  # a multiline
//  # string""".stripMargin('#')
//// ==
//"""this is
//  |a multiline
//  |string
//""".stripMargin
//// ==
//"""this is
//a multiline
//string
//"""
//"""four score and
//  |seven years ago
//  |our fathers
//""".stripMargin.replaceAll("\\s", "\'")
////""".stripMargin.replaceAll(32.toChar.toString, "\'")
//
//val s = "   eggs  , milk   , butter  , Coco Puffs"
//s.split(',').map(_.trim)
//s.split(',')
//"\\s+"
//"hello world,      this is Al".split("\\s+") // 正则表达式 \s 空白符包括回车换行
//"hello world,      this is Al".split(' ') // 字符
//
//case class Student(name: String, score: Int)
//
//val hannah = Student("zl", 95)
//println(s"${hannah.name.toString} has a score of $hannah.score")
