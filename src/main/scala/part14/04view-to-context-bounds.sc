

object Serialization {

  type Writable[A] = A => Rem[A]

  case class Rem[A](value: A) {
    def serialized: String = s"--$value--"
  }

  implicit val fromInt: Writable[Int] = (i: Int) => Rem(i)
  implicit val fromDouble: Writable[Double] = (d: Double) => Rem(d)
  implicit val fromString: Writable[String] = (s: String) => Rem(s)

  implicit def fromFloat(f: Float): Rem[Float] = Rem(f)
}

object RemoteConnection {

  import Serialization._

  def write[T: Writable](t: T) = {
    System.err.println(t.serialized)
  }

  def write1[T](t: T)(implicit f: T => Rem[T]): Unit = {
    System.err.println(t.serialized)
  }
}

RemoteConnection.write1(100)
RemoteConnection.write1(3.14F)
RemoteConnection.write1(0.001D)
RemoteConnection.write1("bai wen")