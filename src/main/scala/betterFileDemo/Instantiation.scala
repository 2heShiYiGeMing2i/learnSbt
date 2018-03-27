package betterFileDemo

import java.io.{File => JFile}
import java.net.URL

import better.files._

/**
  * Created by zhaolei on 2018/3/9
  */
class Instantiation {

  val fileName = "fileNameTest"
  private val f = File(fileName)
  f.newInputStream.autoClosed
  private val url = new URL("url")
  url.openStream().autoClosed.apply(in => System.err.println(in.toString))

  f.newBufferedReader.autoClosed

}
