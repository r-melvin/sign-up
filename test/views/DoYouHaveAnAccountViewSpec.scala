package views

import forms.YesNoForm
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import views.helpers.ViewSpecHelper
import views.html.do_you_have_an_account

class DoYouHaveAnAccountViewSpec extends ViewSpecHelper {

  lazy val document: Document = Jsoup.parse(do_you_have_an_account(YesNoForm.yesNoForm).body)


  "Calling the DoYouHaveAccount view" should {

    "have the title 'Do you have an account?'" in {
      document.title mustBe "Do you have an account?"
    }

    "have a single heading" which {
      lazy val heading = document.select("h1")

      "should be the only h1" in {
        heading.size mustBe 1
      }

      "should have the text 'Do you already have an account with us?'" in {
        heading.text mustBe "Do you already have an account with us?"
      }
    }

    "have 2 radio buttons" in {
      document.select("input[type=\"radio\"]").size mustBe 2
    }
  }
}
