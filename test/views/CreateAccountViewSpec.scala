package views

import forms._
import org.jsoup.Jsoup
import play.api.test.CSRFTokenHelper._
import utils.TestHelper
import views.html.create_account

class CreateAccountViewSpec extends TestHelper {

  lazy val emptyForm = NewAccountForm.newAccountForm
  lazy val request = fakeRequest.withCSRFToken

  "Calling the CreateAccount view" should {
    lazy val document = Jsoup.parse(create_account(emptyForm)(request, messages).body)

    "have the title 'Create Account'" in {
      document.title shouldEqual "Create Account"
    }

    "have a single heading" which {
      lazy val heading = document.select("h1")

      "should be the only h1" in {
        heading.size shouldEqual 1
      }

      "should have the text 'Create an account'" in {
        heading.text shouldBe "Create an account"
      }
    }

    "have a field for firstName" which {
      val formField = document.select("#firstName")

      "should accept text" in {
        formField.attr("type") shouldEqual "text"
      }

      "should have a label" which {
        val label = document.select("label").first()

        "should have the value 'First Name" in {
          label.text shouldEqual "First Name"
        }

        "should link to the form field" in {
          label.attr("for") shouldEqual formField.attr("id")
        }
      }
    }

    "have a field for lastName" which {
      val formField = document.select("#lastName")

      "should accept text" in {
        formField.attr("type") shouldEqual "text"
      }

      "should have a label" which {
        val label = document.select("label").get(1)

        "should have the value 'Last Name" in {
          label.text shouldEqual "Last Name"
        }

        "should link to the form field" in {
          label.attr("for") shouldEqual formField.attr("id")
        }
      }
    }

    "have a field for email" which {
      val formField = document.select("#email")

      "should accept text" in {
        formField.attr("type") shouldEqual "text"
      }

      "should have a label" which {
        val label = document.select("label").get(2)

        "should have the value 'Email Address" in {
          label.text shouldEqual "Email Address"
        }

        "should link to the form field" in {
          label.attr("for") shouldEqual formField.attr("id")
        }
      }
    }

    "have a field for password" which {
      val formField = document.select("#password")

      "should accept text" in {
        formField.attr("type") shouldEqual "text"
      }

      "should have a label" which {
        val label = document.select("label").get(3)

        "should have the value 'Password" in {
          label.text shouldEqual "Password"
        }

        "should link to the form field" in {
          label.attr("for") shouldEqual formField.attr("id")
        }
      }
    }

    "have a field for confirm password" which {
      val formField = document.select("#confirmPassword")

      "should accept text" in {
        formField.attr("type") shouldEqual "text"
      }

      "should have a label" which {
        val label = document.select("label").get(4)

        "should have the value 'Confirm Password" in {
          label.text shouldEqual "Confirm Password"
        }

        "should link to the form field" in {
          label.attr("for") shouldEqual formField.attr("id")
        }
      }
    }

    "have a Submit button" which {
      val button = document.select("button")

      "should have the text 'Submit" in {
        button.text shouldEqual "Submit"
      }
    }
  }
}
