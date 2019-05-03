package views

import forms._
import utils.TestHelper
import org.jsoup.Jsoup
import play.api.test.CSRFTokenHelper._
import play.api.test._

class DoYouHaveAnAccountViewSpec extends TestHelper {

  lazy val emptyForm = YesNoForm.yesNoForm
  lazy val request = fakeRequest.withCSRFToken

  "Calling the DoYouHaveAccount view" should {
    lazy val document = Jsoup.parse(views.html.do_you_have_an_account(emptyForm)(request, messages).body)

    "have the title 'Do you have an account?'" in {
      document.title shouldEqual "Do you have an account?"
    }

    "have a single heading" which {
      lazy val heading = document.select("h1")

      "should be the only h1" in {
        heading.size shouldEqual 1
      }

      "should have the text 'Do you already have an account with us?'" in {
        heading.text shouldBe "Do you already have an account with us?"
      }
    }

    "have 2 radio buttons" in {
      document.select("input[type=\"radio\"]").size shouldEqual 2
    }
  }
}
