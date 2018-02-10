
package part15 {

  /**
    * 对象O1 是 静态的 编译后的数据
    * public final class part15.O1$ {
    * public static part15.O1$ MODULE$;
    * public static {};
    * }
    */
  object O1 { // public final class part15.O1
    /**
      * public class part15.O1$O2$ {
      * public static part15.O1$O2$ MODULE$;
      * public static {};
      * public java.lang.String name();
      * public part15.O1$O2$();
      */
    object O2 {
      // 静态编译 也就是 object static
      val name = "name"
    }

    /**
      * public class part15.O1$C1 {
      * public java.lang.String name();
      * public part15.O1$C1();
      * }
      * 非固定元素 非静态的元素
      */
    class C1 {
      // 正常编译的对象
      val name = "name"
    }

  }

}

class C7 {
  val name: String = part15.O1.O2.name // Okay - 路径表达式指向某一字段  静态的name
  type C1 = part15.O1.C1 // Okay - 路径表达式指向某一“叶子”类  静态的类型
  val c1 = new part15.O1.C1 // Okay - 路径表达式指向某一“叶子”类  new一个c1的对象
  //  val name2 = part15.O1.C1 // ERROR - P1.O1.C1并不固定

  System.err.println(name)
  private val c: C1 = new C1
  System.err.println(c.name)
  System.err.println(c1.name)

}

