
object Serialization {

  case class Writable(value: Any) {
    def serialized: String = s" --$value--"
  }

  implicit def fromInt(i: Int) = Writable(i)

  implicit def fromFloat(f: Float) = Writable(f)

  implicit def fromDouble(d: Double) = Writable(d)

  implicit def fromString(s: String) = Writable(s)

}

object RemoteConnection {
  import Serialization.Writable

  def write[T](t: T)(implicit ev: T => Writable): Unit = {
    System.err.println(t.serialized)
  }

  def write1[T <% Writable](t: T): Unit = {
    System.err.println(t.serialized)
  }
}

RemoteConnection.write1(100)
RemoteConnection.write1(3.14F)
RemoteConnection.write1(0.001D)
RemoteConnection.write1("bai wen")