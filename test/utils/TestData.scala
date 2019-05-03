package utils

import models.UserData
import models.forms.Credentials

object TestData {

  val credentials = Credentials("example@example.com", "p2ssword")

  val invalidJson = """{"invalid":"json"}"""

  val userData = UserData("Joe", "Bloggs", "example@example.com", "p2ssword")

}
