package part14

/**
  * Created by zhaolei on 2018/2/7
  */
object Test {
  val arr: List[_ <: Number] = List()
  // ////////编译结果  等同于 ? extend ****
  //  PS E:\WorkSpace\Z_TEST\learnSbt\target\scala-2.12\classes\part14> javap -p .\ZhaoLei.class
  //  Compiled from "ZhaoLei.java"
  //  public class part14.ZhaoLei {
  //    public java.util.ArrayList<? extends java.lang.Number> list;
  //    public part14.ZhaoLei();
  //  }
  //  PS E:\WorkSpace\Z_TEST\learnSbt\target\scala-2.12\classes\part14> javap -p .\Test.class
  //  Compiled from "Test.scala"
  //  public final class part14.Test {
  //    public static scala.collection.immutable.List<? extends java.lang.Number> arr();


}





