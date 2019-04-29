package models

import play.api.libs.json.Json

case class UserData(firstName: String, lastName: String, email: String, password: String)

object UserData {
  implicit val formats = Json.format[UserData]
}
