package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.CSRFTokenHelper._
import play.api.test.Helpers._
import play.api.test._

class YourDetailsControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  val controller = app.injector.instanceOf[YourDetailsController]

  val testGetRequest = FakeRequest(GET, "/your-details").withCSRFToken

  def postRequest(answer: String) = FakeRequest(POST, "/your-details").withFormUrlEncodedBody(("answer", answer)).withCSRFToken

  "show" should {
    "render the index page from a new instance of home controller" in {
      val result = controller.show()(testGetRequest)

      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }

//  "submit" should {
//    "go to the login page when answering yes" in {
//      val result = controller.submit()(postRequest("yes"))
//      status(result) mustBe SEE_OTHER
//      redirectLocation(result) mustBe Some(routes.LoginPageController.show().url)
//    }
//
//    "got to the create account page when answering no" in {
//      val result = controller.submit()(postRequest("no"))
//      status(result) mustBe SEE_OTHER
//      redirectLocation(result) mustBe Some(routes.CreateAccountController.show().url)
//    }
//
//  }
}
