package models.forms

import play.api.libs.json._

case class YesNoModel(answer: Option[String]) {
  def getYesNoValue: String = answer.getOrElse("no")
}

object YesNoModel {
  implicit val format: OFormat[YesNoModel] = Json.format[YesNoModel]
}
