package controllers.helpers

import akka.actor.ActorSystem
import org.scalatestplus.play.PlaySpec

import scala.concurrent.ExecutionContext

trait ControllerSpecHelper extends PlaySpec {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = ExecutionContext.Implicits.global

}
