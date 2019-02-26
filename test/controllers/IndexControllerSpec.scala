package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class IndexControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  object TestIndexController extends IndexController(stubMessagesControllerComponents())

  val testGetRequest = FakeRequest(GET, "/")
  val testPostRequest = FakeRequest(POST, "/")

  "GET /" should {
    "render the index page from a new instance of home controller" in {
      val result = TestIndexController.show()(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "POST /" should {
    "return a Not Implemented" in {
      val result = TestIndexController.submit()(testPostRequest)

      status(result) mustBe NOT_IMPLEMENTED
      //redirectLocation(result) mustBe Some(routes.SecondController.show().url)
    }
  }

}
