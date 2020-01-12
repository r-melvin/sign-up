package forms

import forms.utils.ConstraintUtil._
import models.NewAccountModel
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.{Constraint, Invalid, Valid}


object NewAccountForm {

  val userFirstName = "firstName"
  val userLastName = "lastName"
  val email = "email"
  val password = "password"
  val confirmPassword = "confirmPassword"
  val maxLength = 21
  val minLength = 7

  // http://emailregex.com/
  // n.b. this regex rejects upper case characters
  val emailRegex = """^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\
    |x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[
    |a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4]
    |[0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\
    |x09\x0b\x0c\x0e-\x7f])+)\])$""".stripMargin

  val firstNameNotEntered: Constraint[String] = Constraint("first_name.not_entered")(
    firstName => if (firstName.isEmpty) Invalid("firstName.required.error") else Valid
  )

  val lastNameNotEntered: Constraint[String] = Constraint("last_name.not_entered")(
    lastName => if (lastName.isEmpty) Invalid("lastName.required.error") else Valid
  )

  val emailNotEntered: Constraint[String] = Constraint("email.not_entered")(
    email => if (email.isEmpty) Invalid("email.required.error") else Valid
  )

  val emailInvalid: Constraint[String] = Constraint("email.invalid")(
    email => if (email matches emailRegex) Valid else Invalid("email.format.error")
  )

  val passwordMaxLength: Constraint[String] = Constraint("password.maxLength")(
    password => if (password.length > maxLength) Invalid("password.length.error") else Valid
  )

  val passwordMinLength: Constraint[String] = Constraint("password.maxLength")(
    password => if (password.length < minLength) Invalid("password.length.error") else Valid
  )

  val passwordNotEntered: Constraint[String] = Constraint("password.not_entered")(
    password => if (password.isEmpty) Invalid("password.required.error") else Valid
  )

  val confirmPasswordNotEntered: Constraint[String] = Constraint("password.confirm.not_entered")(
    password => if (password.isEmpty) Invalid("password.confirm.required.error") else Valid
  )

  val newAccountForm: Form[NewAccountModel] = Form(
    mapping(
      userFirstName -> text.verifying(firstNameNotEntered),
      userLastName -> text.verifying(lastNameNotEntered),
      email -> text.verifying(emailNotEntered andThen emailInvalid),
      password -> text.verifying(passwordNotEntered andThen passwordMinLength andThen passwordMaxLength),
      confirmPassword -> text.verifying(confirmPasswordNotEntered andThen passwordMinLength andThen passwordMaxLength)
    )(NewAccountModel.apply)(NewAccountModel.unapply).verifying("password.match.error", account => account.password == account.confirmPassword)
  )

}