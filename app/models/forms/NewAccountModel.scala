package models.forms

import play.api.libs.json.{Json, OFormat}

case class NewAccountModel(firstName: String,
                           lastName: String,
                           email: String,
                           password: String,
                           confirmPassword: String)

object NewAccountModel {
  implicit val formats: OFormat[NewAccountModel] = Json.format[NewAccountModel]
}
