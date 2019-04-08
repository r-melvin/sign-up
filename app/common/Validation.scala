package common

object Validation {

  val mandatoryCheck: String => Boolean = input => input.trim != ""

  val yesNoCheck: String => Boolean = {
    case "Yes" => true
    case "No" => true
    case "" => true
    case _ => false
  }
}
