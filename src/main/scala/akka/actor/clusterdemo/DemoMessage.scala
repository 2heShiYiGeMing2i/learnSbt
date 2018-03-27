package akka.actor.clusterdemo

/**
  * Created by zhaolei on 2018/3/7
  */
object DemoMessage {

  case class DoJob(text: String)

  case class ResultDemo(text: String)

  case class JobFail(reson: String, doJob: DoJob)

  case object RegisterDemo

  case object DemoRunMessage

}
