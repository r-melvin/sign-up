package models.forms

import play.api.libs.json.{Json, OFormat}

case class Credentials(email: String, password: String)

object Credentials {
  implicit val formats: OFormat[Credentials] = Json.format[Credentials]
}