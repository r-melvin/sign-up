package forms

import common.Transformers
import common.Validation
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class ExistingAccount(account: String)

object ExistingAccount {
  val existingAccountForm: Form[ExistingAccount] = Form(
    mapping(
      "account" -> text
        .verifying("existingAccount.error.chooseAnswer", Validation.mandatoryCheck)
        .verifying("existingAccount.error.chooseAnswer", Validation.yesNoCheck)
    )(ExistingAccount.apply)(ExistingAccount.unapply)
  )
}