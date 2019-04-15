package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class NewAccount(firstName: String, lastName: String, email: String, password: String, confirmPassword: String)

object NewAccount {

  val newAccountForm = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText,
      "confirmPassword" -> nonEmptyText
    )(NewAccount.apply)(NewAccount.unapply).verifying("passwords do not match", account => account.password == account.confirmPassword)
  )
}
