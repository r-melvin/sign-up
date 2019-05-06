package forms

import models.forms.NewAccountModel
import org.scalatest.{Matchers, WordSpec}
import play.api.data.FormError

class NewAccountFormSpec extends WordSpec with Matchers {

  "The NewAcountForm" should {

    val form = NewAccountForm.newAccountForm

    "bind with no errors" when {

      "supplied with valid account creation details" in {

        form.bind(Map(
          "firstName" -> "Joe",
          "lastName" -> "Bloggs",
          "email" -> "example@example.com",
          "password" -> "p2ssword",
          "confirmPassword" -> "p2ssword")).errors.size shouldBe 0
      }

      "supplied with a valid NewAccountModel" in {

        form.fill(NewAccountModel("Joe", "Bloggs", "example@example.com", "p2ssword", "p2ssword")).errors.size shouldBe 0
      }
    }

    "bind with errors" when {

      "supplied with no form data" should {

        val boundForm = form.bind(Map(
          "firstName" -> "",
          "lastName" -> "",
          "email" -> "",
          "password" -> "",
          "confirmPassword" -> ""))

        "have 5 errors" in {

          boundForm.errors.size shouldBe 5
        }
      }

      "not supplied with a firstName or lastName" should {

        val boundForm = form.bind(Map(
          "firstName" -> "",
          "lastName" -> "",
          "email" -> "example@example.com",
          "password" -> "p2ssword",
          "confirmPassword" -> "p2ssword"))

        "have 2 errors" in {

          boundForm.errors.size shouldBe 2
        }

        "contain the error message keys 'firstName.required.error' and 'lastName.required.error'" in {

          boundForm.errors.contains(FormError("firstName", "firstName.required.error")) &&
            boundForm.errors.contains(FormError("lastName", "lastName.required.error"))
        }
      }

      "not supplied with an email address" should {

        val boundForm = form.bind(Map(
          "firstName" -> "Joe",
          "lastName" -> "Bloggs",
          "email" -> "",
          "password" -> "p2ssword",
          "confirmPassword" -> "p2ssword"))

        "have 1 error" in {

          boundForm.errors.size shouldBe 1
        }

        "have the error message key 'email.required.error'" in {
          boundForm.errors.contains(FormError("email", "email.required.error"))
        }
      }

      "not supplied with a password" should {

        val boundForm = form.bind(Map(
          "firstName" -> "Joe",
          "lastName" -> "Bloggs",
          "email" -> "example@example.com",
          "password" -> "",
          "confirmPassword" -> "p2ssword"))

        "have 1 error" in {
          boundForm.errors.size shouldBe 1
        }

        "have the error message key 'password.required.error'" in {

          boundForm.errors.contains(FormError("password", "password.required.error"))
        }
      }

      "not supplied with a confirmPassword" should {

        val boundForm = form.bind(Map(
          "firstName" -> "Joe",
          "lastName" -> "Bloggs",
          "email" -> "example@example.com",
          "password" -> "p2ssword",
          "confirmPassword" -> ""))

        "have 1 error" in {
          boundForm.errors.size shouldBe 1
        }

        "have the error message key 'password.confirm.required.error'" in {

          boundForm.errors.contains(FormError("password", "password.confirm.required.error"))
        }
      }

      "password and confirmPassword do not match" should {

        val boundForm = form.bind(Map(
          "firstName" -> "Joe",
          "lastName" -> "Bloggs",
          "email" -> "example@example.com",
          "password" -> "p2ssword",
          "confirmPassword" -> "p3ssword"))

        "have 1 error" in {
          boundForm.errors.size shouldBe 1
        }

        "have the error message key 'password.match.error'" in {

          boundForm.errors.contains(FormError("password", "password.match.error"))
        }
      }
    }
  }
}
