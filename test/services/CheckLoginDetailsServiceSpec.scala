package services

import connectors.CheckLoginDetailsConnector.{LoginDetailsDoNotMatch, LoginDetailsMatch, LoginDetailsNotFound}
import connectors.mocks.MockCheckLoginDetailsConnector
import models.LoginDetailsModel
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.{await, defaultAwaitTimeout}

import scala.concurrent.Future

class CheckLoginDetailsServiceSpec extends PlaySpec with MockCheckLoginDetailsConnector {

  object TestService extends CheckLoginDetailsService(mockCheckLoginDetailsConnector)

  "checkLoginDetails" should {
    "return Right(LoginDetailsMatch)" when {
      "successfully matched in the backend" in {
        val testLoginDetails = LoginDetailsModel("ui", "op")

        mockCheckLoginDetails(testLoginDetails)(Future.successful(Right(LoginDetailsMatch)))

        val result = TestService.checkLoginDetails(testLoginDetails)

        await(result) mustBe Right(LoginDetailsMatch)
      }
    }

    "return Left(LoginDetailsDoNotMatch)" when {
      "do not match in the backend" in {
        val testLoginDetails = LoginDetailsModel("ui", "op")

        mockCheckLoginDetails(testLoginDetails)(Future.successful(Left(LoginDetailsDoNotMatch)))

        val result = TestService.checkLoginDetails(testLoginDetails)

        await(result) mustBe Left(LoginDetailsDoNotMatch)
      }
    }

    "return Left(LoginDetailsNotFound)" when {
      "login details are not found in the backend" in {
        val testLoginDetails = LoginDetailsModel("ui", "op")

        mockCheckLoginDetails(testLoginDetails)(Future.successful(Left(LoginDetailsNotFound)))

        val result = TestService.checkLoginDetails(testLoginDetails)

        await(result) mustBe Left(LoginDetailsNotFound)
      }
    }

    "expect an exception to be thrown" when {
      "the backend fails" in {
        val testLoginDetails = LoginDetailsModel("ui", "op")

        mockCheckLoginDetails(testLoginDetails)(Future.failed(new Exception("qwertyuiop")))

        intercept[Exception](await(TestService.checkLoginDetails(testLoginDetails)))
      }
    }
  }

}
