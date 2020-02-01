package views

import forms.NewAccountForm
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import views.helpers.ViewSpecHelper
import views.html.create_account

class CreateAccountViewSpec extends ViewSpecHelper {

  lazy val document: Document = Jsoup.parse(create_account(NewAccountForm.newAccountForm).body)

  "Calling the CreateAccount view" should {
    "have the title 'Create Account'" in {
      document.title mustBe "Create Account"
    }

    "have a single heading" which {
      lazy val heading = document.select("h1")

      "should be the only h1" in {
        heading.size mustBe 1
      }

      "should have the text 'Create an account'" in {
        heading.text mustBe "Create an account"
      }
    }

    "have a field for firstName" which {
      val formField = document.select("#firstName")

      "should accept text" in {
        formField.attr("type") mustBe "text"
      }

      "should have a label" which {
        val label = document.select("label").first()

        "should have the value 'First Name" in {
          label.text mustBe "First Name"
        }

        "should link to the form field" in {
          label.attr("for") mustBe formField.attr("id")
        }
      }
    }

    "have a field for lastName" which {
      val formField = document.select("#lastName")

      "should accept text" in {
        formField.attr("type") mustBe "text"
      }

      "should have a label" which {
        val label = document.select("label").get(1)

        "should have the value 'Last Name" in {
          label.text mustBe "Last Name"
        }

        "should link to the form field" in {
          label.attr("for") mustBe formField.attr("id")
        }
      }
    }

    "have a field for email" which {
      val formField = document.select("#email")

      "should accept text" in {
        formField.attr("type") mustBe "text"
      }

      "should have a label" which {
        val label = document.select("label").get(2)

        "should have the value 'Email Address" in {
          label.text mustBe "Email Address"
        }

        "should link to the form field" in {
          label.attr("for") mustBe formField.attr("id")
        }
      }
    }

    "have a field for password" which {
      val formField = document.select("#password")

      "should accept text" in {
        formField.attr("type") mustBe "text"
      }

      "should have a label" which {
        val label = document.select("label").get(3)

        "should have the value 'Password" in {
          label.text mustBe "Password"
        }

        "should link to the form field" in {
          label.attr("for") mustBe formField.attr("id")
        }
      }
    }

    "have a field for confirm password" which {
      val formField = document.select("#confirmPassword")

      "should accept text" in {
        formField.attr("type") mustBe "text"
      }

      "should have a label" which {
        val label = document.select("label").get(4)

        "should have the value 'Confirm Password" in {
          label.text mustBe "Confirm Password"
        }

        "should link to the form field" in {
          label.attr("for") mustBe formField.attr("id")
        }
      }
    }

    "have a Submit button" which {
      val button = document.select("button")

      "should have the text 'Submit" in {
        button.text mustBe "Submit"
      }
    }
  }
}
