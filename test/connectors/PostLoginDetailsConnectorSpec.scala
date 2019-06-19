package connectors

import akka.stream.Materializer
import config.AppConfig
import mockws.{MockWS, Route}
import models.forms.Credentials
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.mvc.{ControllerComponents, Results}
import utils.TestData._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class PostLoginDetailsConnectorSpec extends PlaySpec with ScalaFutures with Results with MockitoSugar with GuiceOneServerPerSuite {

  implicit lazy val materializer: Materializer = app.materializer
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf(classOf[ControllerComponents])
  lazy val appConfig: AppConfig = app.injector.instanceOf(classOf[AppConfig])

  "postLoginDetails" should {

    "return a 200 when successful" in {
      val testRoute = Route {
        case ("POST", _) => controllerComponents.actionBuilder(
          Ok("Login successful")
        )
      }

      val ws = MockWS(testRoute)
      val connector = new PostLoginDetailsConnector(appConfig, ws)

      val result = Await.result(connector.postLoginDetails(credentials), 5 seconds)
      result mustBe Right("Login successful")
    }

    "return a 404 when login details are not found" in {
      val testroute = Route {
        case ("POST", _) => controllerComponents.actionBuilder(
          NotFound("Login details not found")
        )
      }

      val ws = MockWS(testroute)
      val connector = new PostLoginDetailsConnector(appConfig, ws)

      val result = Await.result(connector.postLoginDetails(Credentials("","")), 5 seconds)
      result mustBe Left("Login details not found")
    }
  }

  
}
