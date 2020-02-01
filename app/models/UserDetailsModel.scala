package models

import play.api.libs.json._

case class UserDetailsModel(firstName: String, lastName: String, loginDetails: LoginDetailsModel)

object UserDetailsModel {

  implicit val writes: OWrites[UserDetailsModel] =
    (userDetails: UserDetailsModel) =>
      Json.obj(
        "firstName" -> userDetails.firstName,
        "lastName" -> userDetails.lastName,
        "email" -> userDetails.loginDetails.email,
        "hashedPassword" -> userDetails.loginDetails.hashedPassword
      )

}
