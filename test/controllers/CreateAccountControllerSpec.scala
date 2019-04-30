package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.CSRFTokenHelper._
import play.api.test.Helpers._
import play.api.test._

class CreateAccountControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  val controller = app.injector.instanceOf[CreateAccountController]

  val testGetRequest = FakeRequest("GET ", "/create-account").withCSRFToken

  def postRequest(firstName: String,
                  lastName: String,
                  email: String,
                  password: String,
                  confirmPassword: String) =
    FakeRequest("POST", "/create-account").withFormUrlEncodedBody(
      ("firstname", firstName),
      ("lastName", lastName),
      ("email",email),
      ("password",password),
      ("confirmPassword", confirmPassword)
    ).withCSRFToken

  "show" should {
    "render the login page from a new instance of LoginPageController" in {
      val result = controller.show()(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "submit" should {
    "return a 400 when invalid form data is submitted" in {
      val result = controller.submit()(postRequest("", "", "fhkdjkdsf", "p2ssword", "p3ssword"))

      status(result) mustBe BAD_REQUEST
    }

    "return a 200 when valid data is submitted" in {
      val result = controller.submit()(postRequest("joseph", "bloggs", "example@example.com", "p2ssword", "p2ssword"))

      status(result) mustBe OK
    }
  }
}
