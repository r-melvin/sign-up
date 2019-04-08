package models.forms

import play.api.libs.json.{Json, OFormat}

case class ExistingAccount(account: Boolean)

object ExistingAccount {
  implicit val format: OFormat[ExistingAccount] = Json.format[ExistingAccount]
}