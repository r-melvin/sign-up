package forms

import models.forms.Credentials
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

object LoginForm {

  val loginForm: Form[Credentials] = Form(
    mapping(
      "email" -> text
        .verifying("email.required.error", _.nonEmpty)
        .verifying(emailAddress(errorMessage = "email.format.error")),
      "password" -> text
        .verifying("password.required.error", _.length != 0)
        .verifying("password.length.error", _.length > 7)
        .verifying("password.length.error", _.length < 21)
    )(Credentials.apply)(Credentials.unapply)
  )
}

