package controllers

import forms.YesNoForm._
import utils.ComponentSpecHelper
import play.api.test.Helpers._

class DoYouHaveAnAccountControllerISpec extends ComponentSpecHelper {

  "GET /have-an-account" should {
    "return an OK" in {
      val res = get("/have-an-account")

      res must have(
        httpStatus(OK)
      )
    }
  }

  "POST /have-an-account" when {
    "form value is YES" should {
      "redirect to Login page" in {
        val res = post("/have-an-account")(answer -> option_yes)

        res must have(
          httpStatus(SEE_OTHER),
          redirectUri(routes.LoginController.show().url)
        )
      }
    }

    "form value is NO" should {
      "redirect to Create Account page" in {

        val res = post("/have-an-account")(answer -> option_no)

        res must have(
          httpStatus(SEE_OTHER),
          redirectUri(routes.CreateAccountController.show().url)
        )
      }
    }
  }

}
