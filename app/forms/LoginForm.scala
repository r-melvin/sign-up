package forms

import models.LoginDetailsModel
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

object LoginForm {

  val loginForm: Form[LoginDetailsModel] = Form(
    mapping(
      "email" -> text
        .verifying("email.required.error", _.nonEmpty)
        .verifying(emailAddress(errorMessage = "email.format.error")),
      "password" -> text
        .verifying("password.required.error", _.length != 0)
        .verifying("password.length.error", _.length > 7)
        .verifying("password.length.error", _.length < 21)
    )(LoginDetailsModel.apply)(LoginDetailsModel.unapply)
  )
}

