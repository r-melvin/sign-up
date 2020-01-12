package controllers

import controllers.helpers.ControllerSpecHelper
import play.api.mvc.{AnyContentAsFormUrlEncoded, RequestHeader}
import play.api.test.CSRFTokenHelper._
import play.api.test.Helpers._
import play.api.test._

class DoYouHaveAnAccountControllerSpec extends ControllerSpecHelper {

  object TestController extends DoYouHaveAnAccountController(stubMessagesControllerComponents())

  val testGetRequest: RequestHeader = FakeRequest(GET, "/").withCSRFToken

  def postRequest(answer: String): FakeRequest[AnyContentAsFormUrlEncoded] = FakeRequest(POST, "/").withFormUrlEncodedBody(("answer", answer))

  "show" should {
    "render the index page from a new instance of home controller" in {
      val result = TestController.show(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "submit" should {
    "go to the login page when answering yes" in {
      val result = TestController.submit(postRequest("yes"))

      status(result) mustBe SEE_OTHER
      redirectLocation(result) mustBe Some(routes.LoginController.show().url)
    }

    "go to the create account page when answering no" in {
      val result = TestController.submit(postRequest("no"))

      status(result) mustBe SEE_OTHER
      redirectLocation(result) mustBe Some(routes.CreateAccountController.show().url)
    }

  }
}
