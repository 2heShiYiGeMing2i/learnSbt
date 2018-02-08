trait Config {
  // 配置特质 定义数据和行为
  def load: Unit

  val text: String
}

trait InMemoryConfig extends Config {
  // 一个具体的配置特质 具体和行为
  lazy val text = "hello"

  def load: Unit = System.err.println(s"load: $text")
}

trait Context {
  // 内容特质 具体的内容
}

trait MyContext extends Context {
  self: Config =>
  def welcome = this.text
}



object Env extends MyContext with InMemoryConfig

Env.load
System.err.println(Env.text)