package partthree

/**
  * Created by zhaolei on 2018/1/4
  */
object TryCatch {
  def main(args: Array[String]) = {
    args foreach (arg => countLines(arg))
    countLines("somefile.txt")
  }


  import scala.io.Source

  def countLines(fileName: String) = {
    println() // Add a blank line for legibility
    var source: Option[Source] = None
    var size: Int = 0
    try {
      source = Some(Source.fromFile(fileName))
      size = source.get.getLines.size
      System.err.println(s"file $fileName has $size lines")
    } catch {
      case e: Throwable => System.err.println(e.toString)
      //      case NonFatal(ex) => println(s"Non fatal exception! $ex")
    } finally {
      for (s <- source) {
        println(s"Closing $fileName...")
        s.close
      }
    }
    assert(100 == 100)
    size
  }

}
