package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.CSRFTokenHelper._
import play.api.test.Helpers._
import play.api.test._

class LoginPageControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  val controller = app.injector.instanceOf[LoginPageController]

  val testGetRequest = FakeRequest("GET ", "/login").withCSRFToken

  def postRequest(email: String, password: String) =
    FakeRequest("POST", "/login").withFormUrlEncodedBody(("email",email),("password",password)).withCSRFToken

  "show" should {
    "render the login page from a new instance of LoginPageController" in {
      val result = controller.show()(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "submit" should {
    "return a 400 when invalid form data is submitted" in {
      val result = controller.submit()(postRequest("", ""))

      status(result) mustBe BAD_REQUEST
    }

    "return a 200 when valid data is submitted" in {
      val result = controller.submit()(postRequest("example@example.com", "p2ssword"))

      status(result) mustBe OK
    }
  }
}
