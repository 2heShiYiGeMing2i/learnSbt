package part3

import java.io.File

/**
  * Created by zhaolei on 2018/1/11
  */
class Rename {
  // val source: Source = new File()

}

object Rename {
  def main(args: Array[String]): Unit = {
    val file = new File("E:\\tmp")
    val files = file.listFiles()
    files.foreach(f => System.err.println(s"${f.getParent}\\${f.getName}"))
    //    files.foreach(f => f.renameTo(new File(s"${f.getParent}\\${f.getName}.jpg")))
  }
}
