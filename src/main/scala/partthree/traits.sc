class ServiceImportante(name: String) {
  def work(i: Int): Int = {
    System.err.println(s"ServiceImportante: Doing important work! $i")
    i + 1
  }
}

trait Logging {
  def info(message: String): Unit

  def warning(message: String): Unit

  def error(message: String): Unit
}

trait StdoutLogging extends Logging {
  override def info(message: String): Unit = System.err.println(s"INFO: $message")

  override def warning(message: String): Unit = System.err.println(s"WARNING: $message")

  override def error(message: String): Unit = System.err.println(s"ERROR: $message")
}

val service1 = new ServiceImportante("uno")
(1 to 3).foreach(i => System.err.println(s"Result: ${service1.work(i)}"))