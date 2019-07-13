package forms

import models.{No, Yes, YesNoModel}
import play.api.data.Forms._
import play.api.data._
import play.api.data.format.Formatter

object YesNoForm {

  val answer = "answer"

  val yes = "yes"

  val no = "no"

  def yesNoForm: Form[YesNoModel] = Form(
    single(
      answer -> of[YesNoModel](yesNoFormatter("answer.error.chooseAnswer"))
    )
  )

  def yesNoFormatter(error: String): Formatter[YesNoModel] =
    new Formatter[YesNoModel] {
      override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], YesNoModel] = {
        data.get(key) match {
          case Some(`yes`) => Right(Yes)
          case Some(`no`) => Right(No)
          case _ => Left(Seq(FormError(key, error)))
        }
      }

      override def unbind(key: String, value: YesNoModel): Map[String, String] = {
        val stringValue = value match {
          case Yes => yes
          case No => no
        }

        Map(key -> stringValue)
      }
    }


}

