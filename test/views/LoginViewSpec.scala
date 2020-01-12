package views

import forms.LoginForm
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import views.helpers.ViewSpecHelper
import views.html.login

class LoginViewSpec extends ViewSpecHelper {

  lazy val document: Document = Jsoup.parse(login(LoginForm.loginForm).body)

  "Calling the DoYouHaveAccount view" should {

    "have the title 'Login'" in {
      document.title mustBe "Login"
    }

    "have a single heading" which {
      lazy val heading = document.select("h1")

      "should be the only h1" in {
        heading.size mustBe 1
      }

      "should have the text 'Login'" in {
        heading.text mustBe "Login"
      }
    }

    "have a field for email" which {
      val formField = document.select("#inputEmail")

      "should accept text" in {
        formField.attr("type") mustBe "text"
      }

      "should have a label" in {
        document.select("label").first().attr("for") mustBe formField.attr("id")
      }
    }

    "have a field for password" which {
      val formField = document.select("#inputPassword")

      "should accept text" in {
        formField.attr("type") mustBe "text"
      }

      "should have a label" in {
        document.select("label").get(1).attr("for") mustBe formField.attr("id")
      }
    }

    "have a submit button" which {
      val button = document.select("button")

      "should have the text 'Submit" in {
        button.text mustBe "Submit"
      }
    }
  }
}
