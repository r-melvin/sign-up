package forms

import models.Yes
import org.scalatest.{Matchers, WordSpec}
import play.api.data.FormError

class YesNoFormSpec extends WordSpec with Matchers {

  "The YesNoForm" should {

    val form = YesNoForm.yesNoForm

    "bind with no errors" when {
      "Yes is selected as an answer" in {
        form.bind(Map("answer" -> "yes")).errors.size shouldEqual 0
      }

      "No is selected as an answer" in {
        form.bind(Map("answer" -> "no")).errors.size shouldEqual 0
      }

      "supplied with a valid YesNoModel" in {
        form.fill(Yes).errors.size shouldEqual 0
      }
    }

    "bind with errors" when {
      "no answer is provided" should {
        val boundForm = form.bind(Map("answer" -> ""))

        "have 1 error" in {
          boundForm.errors.size shouldEqual 1
        }

        "have the error message key 'answer.error.chooseAnswer'" in {
          boundForm.errors.contains(FormError("answer", "answer.error.chooseAnswer"))
        }

      }
    }
  }
}
