package forms

import models.LoginDetailsModel
import org.scalatest.{Matchers, WordSpec}
import play.api.data.FormError

class LoginFormSpec extends WordSpec with Matchers{

  "The LoginForm" should {

    val form = LoginForm.loginForm

    "bind with no errors when supplied with a valid email address and password" in {
      form.bind(Map("email" -> "example@example.com", "password" -> "p2ssword")).errors.size shouldEqual 0
    }

    "bind with no errors when supplied with a valid Crendentials" in {
      form.fill(LoginDetailsModel("example@example.com", "p2ssword")).errors.size shouldEqual 0
    }

    "bind with an error" when {

      "not supplied an email address" should {
        val boundForm = form.bind(Map("email" -> "", "password" -> "p2ssword"))

        "have 1 error" in {
          boundForm.errors.size shouldEqual 2
        }

        "have the error message key 'email.required.error'" in {
          assert(boundForm.errors.contains(FormError("email", "email.required.error")))
        }
      }

      "supplied with an invalid email address" should {
        val boundForm = form.bind(Map("email" -> "anEmailAddress", "password" -> "p2ssword"))

        "have 1 error" in {
          boundForm.errors.size shouldEqual 1
        }

        "have the error message key 'email.format.error'" in {
          assert(boundForm.errors.contains(FormError("email", "email.format.error")))
        }
      }

      "not supplied a password" should {
        val boundForm = form.bind(Map("email" -> "example@exaple.com", "password" -> ""))

        "have 1 error" in {
          boundForm.errors.size shouldEqual 2
        }

        "have the error message key 'password.required.error'" in {
          assert(boundForm.errors.contains(FormError("password", "password.required.error")))
        }
      }

      "supplied a password that is too short" should {
        val boundForm = form.bind(Map("email" -> "example@exaple.com", "password" -> "pword"))

        "have 1 error" in {
          boundForm.errors.size shouldEqual 1
        }

        "have the error message key 'password.length.error'" in {
          assert(boundForm.errors.contains(FormError("password", "password.length.error")))
        }
      }

      "supplied a password that is too long" should {
        val boundForm = form.bind(Map("email" -> "example@exaple.com", "password" -> "thisPasswordIsTooLong"))

        "have 1 error" in {
          boundForm.errors.size shouldEqual 1
        }

        "have the error message key 'password.required.error'" in {
          assert(boundForm.errors.contains(FormError("password", "password.length.error")))
        }
      }
    }
  }

}
