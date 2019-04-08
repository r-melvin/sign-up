package views

import form.ExistingAccountForm
import utils.ViewSpecHelper
import form.ExistingAccountForm._
import org.jsoup.Jsoup
import views.html.index

class IndexViewSpec extends ViewSpecHelper {

  lazy val emptyForm = ExistingAccountForm.existingAccountForm

  "Calling the index view" should {
    lazy val document = Jsoup.parse(views.html.index(emptyForm)(fakeRequest, messages).body)

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
