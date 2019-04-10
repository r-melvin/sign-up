package forms

import models.{No, Yes, YesNo}
import play.api.data.{Form, FormError}
import play.api.data.Forms._
import play.api.data.format.Formatter

object YesNoForm {

  val option_yes: String = "yes"

  val option_no: String = "no"

  val yesNo: String = "yes_no"

  def yesNoMapping(error: String): Formatter[YesNo] = new Formatter[YesNo] {
    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], YesNo] = {
      data.get(key) match {
        case Some(`option_yes`) => Right(Yes)
        case Some(`option_no`) => Right(No)
        case _ => Left(Seq(FormError(key, error)))
      }
    }

    override def unbind(key: String, value: YesNo): Map[String, String] = {
      val stringValue = value match {
        case Yes => option_yes
        case No => option_no
      }

      Map(key -> stringValue)
    }
  }

  val yesNoForm: Form[YesNo] = Form(
    single(
      yesNo -> of(yesNoMapping(""))
    )
  )

}
