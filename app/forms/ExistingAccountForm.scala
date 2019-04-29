package forms

import models._
import play.api.data.Forms._
import play.api.data._

object ExistingAccountForm {
  def existingAccountForm: Form[ExistingAccountModel] = Form(
    mapping(
      "existingAccount" -> optional(text).verifying("existingAccount.error.chooseAnswer", {_.isDefined})
    )(ExistingAccountModel.apply)(ExistingAccountModel.unapply)
  )
}
