package form

import models.forms.Login
import play.api.data.Form
import play.api.data.Forms._

object LoginForm {

  val loginForm: Form[Login] = Form(mapping(
    "userName" -> text
      .verifying("loginForm.form.error.userNameRequired", _.length > 0)
      ,
    "password" -> text
      .verifying("loginForm.form.error.passwordRequired", _.length > 0)
  )(Login.apply)(Login.unapply)
  )
}