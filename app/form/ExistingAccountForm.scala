package form

import models.forms.ExistingAccount
import common.Transformers
import common.Validation
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

object ExistingAccountForm {

  val existingAccountForm: Form[ExistingAccount] = Form(
    mapping(
      "account" -> text
        .verifying("existingAccount.error.chooseAnswer", Validation.mandatoryCheck)
        .verifying("existingAccount.error.chooseAnswer", Validation.yesNoCheck)
        .transform(Transformers.stringToBoolean, Transformers.booleanToString)
    )(ExistingAccount.apply)(ExistingAccount.unapply)
  )
}