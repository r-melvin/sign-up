package forms

import models.{No, Yes, YesNoModel}
import play.api.data.Forms.{of, single}
import play.api.data.format.Formatter
import play.api.data.{Form, FormError}

object YesNoForm {

  val answer = "answer"

  val option_yes: String = "yes"

  val option_no: String = "no"

  def yesNoFormatter(error: String): Formatter[YesNoModel] = new Formatter[YesNoModel] {
    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], YesNoModel] = {
      data.get(key) match {
        case Some(`option_yes`) => Right(Yes)
        case Some(`option_no`) => Right(No)
        case _ => Left(Seq(FormError(key, error)))
      }
    }

    override def unbind(key: String, value: YesNoModel): Map[String, String] = {
      val stringValue = value match {
        case Yes => option_yes
        case No => option_no
      }

      Map(key -> stringValue)
    }
  }

  val yesNoForm: Form[YesNoModel] = Form(
    single(
      answer -> of(yesNoFormatter("answer.error.chooseAnswer"))
    )
  )

}

