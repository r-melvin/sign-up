package models

case class NewAccountModel(firstName: String,
                           lastName: String,
                           email: String,
                           password: String,
                           confirmPassword: String)

