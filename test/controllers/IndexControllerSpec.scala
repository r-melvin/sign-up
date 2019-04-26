package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._

class IndexControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

 val controller = app.injector.instanceOf[IndexController]

  val testGetRequest = FakeRequest(GET, "/").withCSRFToken
  val testPostRequest = FakeRequest(POST, "/").withCSRFToken

  "GET /" should {
    "render the index page from a new instance of home controller" in {
      val result = controller.show()(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "POST /" should {
    "return a 303" in {
      val result = controller.submit()(testPostRequest)

      status(result) mustBe SEE_OTHER
      redirectLocation(result) mustBe Some(routes.SecondController.show().url)
    }
  }

}
