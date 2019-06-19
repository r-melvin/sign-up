package connectors

import akka.stream.Materializer
import config.AppConfig
import mockws.{MockWS, Route}
import models.UserData
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.libs.json.Json
import play.api.mvc.{ControllerComponents, Results}
import utils.TestData._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

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

      val result = Await.result(connector.storeUserDetails(userData), 5 seconds)

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

      val result = Await.result(connector.storeUserDetails(UserData("", "", "", "")), 5 seconds)
      result mustBe Left("An error occurred")
    }
  }
}
