package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class Login(email: String, password: String)

object Login {

  val loginForm = Form(
    mapping(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    )(Login.apply)(Login.unapply)
  )
}

