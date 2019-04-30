package forms

import models._
import play.api.data.Forms._
import play.api.data._

object YesNoForm {
  def yesNoForm: Form[YesNoModel] = Form(
    mapping(
      "answer" -> optional(text).verifying("answer.error.chooseAnswer", {_.isDefined})
    )(YesNoModel.apply)(YesNoModel.unapply)
  )
}

