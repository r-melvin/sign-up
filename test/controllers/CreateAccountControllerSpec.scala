package controllers

import connectors.StoreUserDetailsConnector.UserDetailsStored
import controllers.helpers.ControllerSpecHelper
import models.NewAccountModel
import play.api.test.CSRFTokenHelper._
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.mocks.MockStoreUserDetailsService

import scala.concurrent.Future

class CreateAccountControllerSpec extends ControllerSpecHelper with MockStoreUserDetailsService {

  object TestController extends CreateAccountController(
    stubMessagesControllerComponents(),
    mockStoreUserDetailsService
  )

  "show" should {
    "render the login page from a new instance of LoginPageController" in {
      val getRequest = FakeRequest("GET ", "/create-account").withCSRFToken

      val result = TestController.show(getRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "submit" should {
    "redirect to YourAccount page when valid data is submitted" in {
      val testNewAccountModel = NewAccountModel("firstName", "lastName", "email@email.com", "password", "password")
      mockStoreUserDetails(testNewAccountModel)(Future.successful(UserDetailsStored))

      val postRequest = FakeRequest("POST", "/create-account")
        .withFormUrlEncodedBody(
          ("firstName", "firstName"),
          ("lastName", "lastName"),
          ("email", "email@email.com"),
          ("password", "password"),
          ("confirmPassword", "password")
        )

      val result = TestController.submit(postRequest)

      status(result) mustBe SEE_OTHER
      redirectLocation(result) mustBe Some(routes.YourDetailsController.show().url)

    }

    "return a BAD_REQUEST when invalid form data is submitted" in {
      val invalidPostRequest = FakeRequest("POST", "/").withCSRFToken

      val result = TestController.submit(invalidPostRequest)

      status(result) mustBe BAD_REQUEST
    }
  }

}
