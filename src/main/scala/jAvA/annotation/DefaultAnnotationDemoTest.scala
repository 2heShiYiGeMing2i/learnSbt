package jAvA.annotation

import org.reflections.Reflections

/**
  * Created by zhaolei on 2018/3/10
  */
class DefaultAnnotationDemoTest {
}

object DefaultAnnotationDemoTest {
  def apply[T <: Any](implicit mf: Manifest[T]): Unit = {
    val clazz = mf.runtimeClass
    if (clazz.isAnnotationPresent(classOf[AnnotationDemoRuntimeType])) {

    } else {
      if (clazz.isAnnotation) {
        System.err.println(s"clazz is annotation class: [${clazz.getClasses}]")
      }
    }
  }

  def sourceIs[T <: Any](implicit mf: Manifest[T]): Unit = {
    //    import scala.collection.JavaConverters._
    val clazzs = new Reflections(strings: _*)
    //    val set = clazzs.getSubTypesOf(classOf[Source]).asScala.toList
    val set = clazzs.getSubTypesOf(classOf[Source])
    set.stream().forEach { cl =>
      val mf = Manifest.classType[Source](cl).runtimeClass
      val annotations = mf.getAnnotations
      if (annotations.nonEmpty) System.err.println(mf.getSimpleName) else System.err.println("empty")
    }


  }

  private val demo1 = new AnnoSourceDemo
  private val demo2 = new AnnoSourceDemo
  private val demo3 = new AnnoSourceDemo
  private val demo4 = new AnnoSourceDemo


  private val d1 = new AnnoRunDemo
  private val d2 = new AnnoRunDemo
  private val d3 = new AnnoRunDemo
  private val d4 = new AnnoRunDemo
  private val d5 = new AnnoRunDemo

  lazy val strings: Seq[String] = Seq {
    "jAvA"
  }


  def main(args: Array[String]): Unit = {
    sourceIs
  }


}
