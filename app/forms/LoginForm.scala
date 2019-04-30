package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class LoginForm(email: String, password: String)

object LoginForm {

  val loginForm = Form(
    mapping(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    )(LoginForm.apply)(LoginForm.unapply)
  )
}

