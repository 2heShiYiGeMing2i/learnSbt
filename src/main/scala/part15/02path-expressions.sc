class C1 {
  var x = "1"

  def setX1(x: String) = this.x = x

  def setX2(x: String) = C1.this.x = x

}

trait T1 {

  class C

  val c1: C = new C
  val c2: C = new T1.this.C
}


val c = new C1
c.setX1("x1")
c.setX1("x2")
System.err.println(c.x)


trait X {
  def setXX(string: String): Unit = {}
}

class C2 extends C1

class C3 extends C2 with X {
  def setX3(x: String): Unit = super.setX1(x)

  def setX4(x: String): Unit = C3.super.setX1(x)

  def setX5(x: String): Unit = super[C2].setX1(x)

  def setX6(x: String): Unit = super[X].setXX(x)

  def setX7(x: String): Unit = C3.super[C2].setX1(x)
}