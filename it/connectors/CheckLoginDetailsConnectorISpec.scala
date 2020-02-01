package connectors

import connectors.CheckLoginDetailsConnector._
import models.LoginDetailsModel
import play.api.test.Helpers._
import stubs.CheckLoginDetailsStub.stubCheckLoginDetails
import utils.ComponentSpecHelper

class CheckLoginDetailsConnectorISpec extends ComponentSpecHelper {

  val testLoginDetails: LoginDetailsModel = LoginDetailsModel("asdfghjkl@qwedrtyuiop.com", "p2ssword")

  val connector: CheckLoginDetailsConnector = app.injector.instanceOf[CheckLoginDetailsConnector]

  "checkLoginDetails" should {
    "return Right(LoginDetailsMatch)" when {
      "the data provided is matched in the database" in {
        stubCheckLoginDetails(testLoginDetails)(NO_CONTENT)

        val res = await(connector.checkLoginDetails(testLoginDetails))

        res mustBe Right(LoginDetailsMatch)
      }
    }

    "return Left(LoginDetailsDoNotMatch)" when {
      "the data provided is not matched in the database" in {
        stubCheckLoginDetails(testLoginDetails)(FORBIDDEN)

        val res = await(connector.checkLoginDetails(testLoginDetails))

        res mustBe Left(LoginDetailsDoNotMatch)
      }
    }

    "return Left(LoginDetailsNotFound)" when {
      "the data provided is not found in the database" in {
        stubCheckLoginDetails(testLoginDetails)(NOT_FOUND)

        val res = await(connector.checkLoginDetails(testLoginDetails))

        res mustBe Left(LoginDetailsNotFound)
      }
    }
  }

}
