package models.forms

import play.api.libs.json.{Json, OFormat}

case class Login(userName: String, passWord: String)

object Login {
  implicit val format: OFormat[Login] = Json.format[Login]
}