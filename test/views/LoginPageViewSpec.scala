package views

import forms._
import org.jsoup.Jsoup
import play.api.test.CSRFTokenHelper._
import utils.TestHelper
import views.html.login

class LoginPageViewSpec extends TestHelper {

  lazy val emptyForm = LoginForm.loginForm
  lazy val request = fakeRequest.withCSRFToken

  "Calling the DoYouHaveAccount view" should {
    lazy val document = Jsoup.parse(login(emptyForm)(request, messages).body)

    "have the title 'Login'" in {
      document.title shouldEqual "Login"
    }

    "have a single heading" which {
      lazy val heading = document.select("h1")

      "should be the only h1" in {
        heading.size shouldEqual 1
      }

      "should have the text 'Login'" in {
        heading.text shouldBe "Login"
      }
    }

    "have a field for email" which {
      val formField = document.select("#inputEmail")

      "should accept text" in {
        formField.attr("type") shouldEqual "text"
      }

      "should have a label" in {
        document.select("label").first().attr("for") shouldEqual formField.attr("id")
      }
    }

    "have a field for password" which {
      val formField = document.select("#inputPassword")

      "should accept text" in {
        formField.attr("type") shouldEqual "text"
      }

      "should have a label" in {
        document.select("label").get(1).attr("for") shouldEqual formField.attr("id")
      }
    }

    "have a submit button" which {
      val button = document.select("button")

      "should have the text 'Submit" in {
        button.text shouldEqual "Submit"
      }
    }
  }
}
