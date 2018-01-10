package partthree

import scala.language.reflectiveCalls
import scala.util.control.NonFatal

/**
  * Created by zhaolei on 2018/1/4
  */
object TryCatchArm {
  def main(args: Array[String]): Unit = {
    args foreach (arg => countLines(arg))
  }


  def countLines(fileName: String) = {
    import scala.io.Source
    println() // 打印空白行，以增加可读性
    manage(Source.fromFile(fileName)) { source => {
      val size = source.getLines.size
      println(s"file $fileName has $size lines")
      if (size > 20) throw new RuntimeException("Big file!")
    }
    }
  }

}

object manage {
  def apply[R <: {def close() : Unit}, T](resource: => R)(f: R => T) = {
    var res: Option[R] = None
    try {
      res = Some(resource) // 加载到缓存中
      f(res.get)
    } catch {
      case NonFatal(ex) => System.err.println(ex)
    } finally {
      if (res.isDefined) {
        System.err.println(s"close resource")
        res.get.close()
      }
    }
  }
}
