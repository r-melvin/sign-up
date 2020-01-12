package controllers

import connectors.CheckLoginDetailsConnector.LoginDetailsMatch
import controllers.helpers.ControllerSpecHelper
import models.LoginDetailsModel
import play.api.test.CSRFTokenHelper._
import play.api.test.Helpers._
import play.api.test._
import services.mocks.MockCheckLoginDetailsService

import scala.concurrent.Future

class LoginControllerSpec extends ControllerSpecHelper with MockCheckLoginDetailsService {

  object TestController extends LoginController(
    stubMessagesControllerComponents(),
    mockCheckLoginDetailsService
  )

  "show" should {
    "render the login page from a new instance of LoginPageController" in {
      val testGetRequest = FakeRequest("GET ", "/login").withCSRFToken
      val result = TestController.show(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "submit" should {
    "redirect to YourAccount page when valid data is submitted" in {
      val postRequest = FakeRequest("POST", "/login").withFormUrlEncodedBody(("email", "example@example.com"), ("password", "p2ssword"))
      val loginDetails = LoginDetailsModel("example@example.com", "p2ssword")

      mockCheckLoginDetails(loginDetails)(Future.successful(Right(LoginDetailsMatch)))

      val result = TestController.submit(postRequest)

      status(result) mustBe SEE_OTHER
      redirectLocation(result) mustBe Some(routes.YourDetailsController.show().url)
    }

    "return a BAD_REQUEST when invalid form data is submitted" in {
      val invalidPostRequest = FakeRequest("POST", "/login").withCSRFToken

      val result = TestController.submit(invalidPostRequest)

      status(result) mustBe BAD_REQUEST
    }
  }
}
