package forms

import models.NewAccountModel
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

object NewAccountForm {

  val newAccountForm: Form[NewAccountModel] = Form(
    mapping(
      "firstName" -> text.verifying("firstName.required.error", _.nonEmpty),
      "lastName" -> text.verifying("lastName.required.error", _.nonEmpty),
      "email" -> text
        .verifying("email.required.error", _.nonEmpty)
        .verifying(emailAddress(errorMessage ="email.format.error")),
      "password" -> text
        .verifying("password.required.error", _.nonEmpty)
        .verifying("password.length.error", _.length > 7)
        .verifying("password.length.error", _.length < 21),
      "confirmPassword" -> text
        .verifying("password.confirm.required.error", _.nonEmpty)
        .verifying("password.length.error", _.length > 7)
        .verifying("password.length.error", _.length < 21)
    )(NewAccountModel.apply)(NewAccountModel.unapply).verifying("password.match.error", account => account.password == account.confirmPassword)
  )
}
