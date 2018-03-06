package akka.actor.mailbox

import akka.actor.{ActorSystem, PoisonPill}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

/**
  * Created by zhaolei on 2018/3/6
  */
class MailDemo(settings: ActorSystem.Settings, config: Config) extends UnboundedPriorityMailbox(PriorityGenerator {
  case 'highpriority => 0
  case 'lowpriority  => 2
  case PoisonPill   => 3
  case otherwise    => 1
})