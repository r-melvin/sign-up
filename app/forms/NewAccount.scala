package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class NewAccount(firstName: String, lastName: String, email: String, password: String, confirmPassword: String)

object NewAccount {

  val newAccountForm = Form(
    mapping(
      "firstName" -> nonEmptyText.verifying("firstName.required.error", _.nonEmpty),
      "lastName" -> nonEmptyText.verifying("lastName.required.error", _.nonEmpty),
      "email" -> email
        .verifying("email.required.error", _.nonEmpty)
        .verifying(emailAddress(errorMessage ="email.format.error")),
      "password" -> nonEmptyText
        .verifying("password.required.error", _.nonEmpty)
        .verifying("password.length.error", _.length > 7)
        .verifying("password.length.error", _.length < 21),
      "confirmPassword" -> nonEmptyText
        .verifying("password.required.error", _.nonEmpty)
        .verifying("password.length.error", _.length > 7)
        .verifying("password.length.error", _.length < 21)
    )(NewAccount.apply)(NewAccount.unapply).verifying("password.match.error", account => account.password == account.confirmPassword)
  )
}
