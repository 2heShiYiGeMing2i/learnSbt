class CSuper {
  def msuper() = System.err.println("c super")
}

class BC extends CSuper {
  def bc() = System.err.println("BC")
}

class C extends CSuper {
  def m() = System.err.println("C")
}

class CSub extends C {
  def msub() = System.err.println("CSub")
}

val s = new CSuper
val c = new C
val sub = new CSub
val bc = new BC
//var f: C => C = (c: C) => new C // 输入本身 输出本身
//f = (c: CSuper) => new CSub // 输入父类(输入是逆变) 输出子类(输出是协变)
var f: C => C = (c: CSuper) => new C // ➍ 输入是父类(逆变) 输出是本身(协变)
f = (c: C) => new CSub // ➎ 输入是本身(逆变) 输出是子类(协变)
//f = (c: CSub) => new CSuper // ➏ 编译错误! 输入是子类(逆变 不准许) 输出是父类(协变 不准许)
//f = (c: CSub) => new C // ➏ 编译错误! 输入是子类(逆变 不准许) 输出是父类(协变 不准许)
//f = (bC: BC) => new C // ➏ 编译错误! 输入是子类(逆变 不准许) 输出是父类(协变 不准许)

