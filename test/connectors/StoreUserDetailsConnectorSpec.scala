package connectors

import akka.actor.Status.Success
import akka.stream.Materializer
import config.AppConfig
import mockws.{MockWS, Route}
import models.UserData
import models.forms.Credentials
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, ControllerComponents, Results}
import utils.TestData._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class StoreUserDetailsConnectorSpec extends PlaySpec with ScalaFutures with Results with MockitoSugar with GuiceOneServerPerSuite {

  implicit lazy val materializer: Materializer = app.materializer
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf(classOf[ControllerComponents])
  lazy val appConfig: AppConfig = app.injector.instanceOf(classOf[AppConfig])

  "storeUserDetails" should {

    "return user's credentials when successful" in {
      val testRoute = Route {
        case ("POST", _) => controllerComponents.actionBuilder(
          Created(Json.toJson(credentials))
        )
      }

      val ws = MockWS(testRoute)
      val connector = new StoreUserDetailsConnector(appConfig, ws)

      val result = Await.result(connector.storeUserDetails(userData, userId), 5 seconds)

      result mustBe Right(Json.toJson(credentials))
    }

    "return a 500 when unsucecssful" in {
      val testRoute = Route {
        case ("POST", _) => controllerComponents.actionBuilder(
          InternalServerError("An error occurred")
        )
      }

      val ws = MockWS(testRoute)
      val connector = new StoreUserDetailsConnector(appConfig, ws)

      val result = Await.result(connector.storeUserDetails(UserData("", "", "", ""), userId), 5 seconds)
      result mustBe Left("An error occurred")
    }
  }
}
