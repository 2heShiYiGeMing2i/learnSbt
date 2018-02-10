class Service {

  var logger: Logger = new Logger

  class Logger {
    def log(message: String): Unit = {
      System.err.println(s"log: $message")
    }
  }

}

val s1 = new Service
val s2 = new Service
//System.err.println(s1.logger)
System.err.println(s2.logger.getClass)