//封闭继承层级与全覆盖匹配

abstract class HttpMethod() {
  def body: String

  def bodyLength: Int
}

case class TTTT(age: Int)

case class Connect(body: String, tttt: TTTT) extends HttpMethod {
  override def bodyLength = body.length
}

case class Delete(body: String) extends HttpMethod {
  val bodyLength = 1000
}

case class Get(body: String) extends HttpMethod {
  override def bodyLength = body.length
}

case class Head(body: String) extends HttpMethod {
  override def bodyLength = body.length
}

case class Options(body: String) extends HttpMethod {
  override def bodyLength = body.length
}

case class Post(body: String) extends HttpMethod {
  override def bodyLength = body.length
}

case class Put(body: String) extends HttpMethod {
  override def bodyLength = body.length
}

case class Trace(body: String) extends HttpMethod {
  override def bodyLength = body.length
}

def handle(method: HttpMethod) = method match {
  case Connect(body, t@TTTT(_: Int)) => s"connect: (length: ${method.bodyLength}) ${body.charAt(1)} age is ${t.age}"
  case Delete(body)                  => s"delete: (length: ${method.bodyLength}) ${body.charAt(1)}"
  case Get(body)                     => s"get: (length: ${method.bodyLength}) $body"
  case Head(body)                    => s"head: (length: ${method.bodyLength}) $body"
  case Options(body)                 => s"options: (length: ${method.bodyLength}) $body"
  case Post(body)                    => s"post: (length: ${method.bodyLength}) $body"
  case Put(body)                     => s"put: (length: ${method.bodyLength}) $body"
  case Trace(body)                   => s"trace: (length: ${method.bodyLength}) $body"
}

val methods = Seq(
  Connect("connect body...", TTTT(10)),
  Delete("delete body..."),
  Get("get body..."),
  Head("head body..."),
  Options("options body..."),
  Post("post body..."),
  Put("put body..."),
  Trace("trace body..."))

methods foreach (method => println(handle(method)))

// val 变量 和 def 方法
// 父类将方法定义为 def 无参的方法 返回相应类型的 这样继承的子类有多种情况选择 可以用val和def
// 但是有区别 https://stackoverflow.com/questions/18887264/what-is-the-difference-between-def-and-val-to-define-a-function
// def 每次调用都会执行
// val再第一次调用的时候就执行好了,然后就不执行了