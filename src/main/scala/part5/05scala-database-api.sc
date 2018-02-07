trait Foo

trait ReadableFoo extends Foo {
  def field: Int
}

case class Bar[+F <: Foo](foo: F) {
  def readFiled(implicit evidence: F <:< ReadableFoo) = foo.field
}

case class Grill[+F <: Foo, +B <: Bar[F]](bar: B) {
  def readFiled(implicit evidence: F <:< ReadableFoo) = bar.readFiled
}