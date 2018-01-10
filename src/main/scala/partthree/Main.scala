package partthree

/**
  * Created by zhaolei on 2018/1/3
  */
object Main {
  def main(args: Array[String]): Unit = {

    //    true should be(true)
    val vl = 4
    assert(vl == 4)

    //    import java.util.Calendar
    //    def isFridayThirteen(cal: Calendar): Boolean = {
    //      val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
    //      val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
    //      // Scala将最后一个表达式的结果值作为该方法的返回结果
    //      (dayOfWeek == Calendar.FRIDAY) && (dayOfMonth == 13)
    //    }
    //    while (!isFridayThirteen(Calendar.getInstance())) {
    //      println("Today isn't Friday the 13th. Lame.")
    //      // sleep for a day
    //      Thread.sleep(5000)
    //    }

    //    @throws
    //    def read(string: String): String = {
    //      val in = new BufferedReader(new InputStreamReader(System.in)).readLine()
    //      in match {
    //        case ""          => "end"
    //        case str: String => System.err.println(in); read(in)
    //      }
    //    }
    //
    //    val in = new BufferedReader(new InputStreamReader(System.in)).readLine()
    //    if (in eq in) System.err.println("equal")
    //    read("")

    //    import Breed._
    //    // 打印所有犬种及其ID列表
    //    println("ID\tBreed")
    //    for (breed <- Breed.values) println(s"${breed.id}\t$breed")
    //    // 打印犬列表
    //    println("\nJust Terriers:")
    //    Breed.values filter (_.toString.endsWith("Terrier")) foreach println
    //
    //    def isTerrier(b: Breed) = b.toString.endsWith("Terrier")
    //
    //    println("\nTerriers Again??")
    //    Breed.values filter isTerrier foreach println
  }
}

class ssss {
  //  def eeee() = {
  //    val a = 100
  //    val b = 339
  //    assertResult(2) {
  //      b - a
  //    }
  //  }
}

//object Breed extends Enumeration {
//
//  type Breed = Value
//  val doberman = Value("Doberman Pinscher")
//  val yorkie = Value("Yorkshire Terrier")
//  val scottie = Value("Scottish Terrier")
//  val dane = Value("Great Dane")
//  val portie = Value("Portuguese Water Dog")
//}

