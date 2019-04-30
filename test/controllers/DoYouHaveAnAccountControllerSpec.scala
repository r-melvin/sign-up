package controllers

import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.mvc.MessagesControllerComponents
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._

class DoYouHaveAnAccountControllerSpec extends PlaySpec with GuiceOneAppPerSuite with MockitoSugar{

  val controller = app.injector.instanceOf[DoYouHaveAnAccountController]

  val testGetRequest = FakeRequest(GET, "/").withCSRFToken

  def postRequest(answer: String) = FakeRequest(POST, "/").withFormUrlEncodedBody(("answer", answer)).withCSRFToken

  "GET /" should {
    "render the index page from a new instance of home controller" in {
      val result = controller.show()(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

  "POST /" should {
    "go to the login page when answering yes" in {
      val result = controller.submit()(postRequest("yes"))
      status(result) mustBe SEE_OTHER
      redirectLocation(result) mustBe Some(routes.LoginPageController.show().url)
    }
  }

  "POST /" should (
    "got to the create account page when answering no" in {
      val result = controller.submit()(postRequest("no"))
      status(result) mustBe SEE_OTHER
      redirectLocation(result) mustBe Some(routes.CreateAccountController.show().url)
    }
  )

}