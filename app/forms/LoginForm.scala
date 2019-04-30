package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class LoginForm(email: String, password: String)

object LoginForm {

  val loginForm = Form(
    mapping(
      "email" -> email
        .verifying("email.required.error", _.nonEmpty)
        .verifying(emailAddress(errorMessage = "email.format.error")),
      "password" -> nonEmptyText
        .verifying("password.required.error", _.nonEmpty)
        .verifying("password.length.error", _.length > 7)
        .verifying("password.length.error", _.length < 21)
    )(LoginForm.apply)(LoginForm.unapply)
  )
}

