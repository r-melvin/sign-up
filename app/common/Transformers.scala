package common

object Transformers {

  val stringToBoolean: String => Boolean = {
    case "Yes" => true
    case _ => false
  }

  val booleanToString: Boolean => String = (input) => if (input) "Yes" else "No"

}
