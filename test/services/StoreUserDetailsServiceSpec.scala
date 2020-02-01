package services

import connectors.StoreUserDetailsConnector.UserDetailsStored
import connectors.mocks.MockStoreUserDetailsConnector
import models.{LoginDetailsModel, NewAccountModel, UserDetailsModel}
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.{await, defaultAwaitTimeout}

import scala.concurrent.Future

class StoreUserDetailsServiceSpec extends PlaySpec with MockStoreUserDetailsConnector {

  object TestService extends StoreUserDetailsService(mockStoreUserDetailsConnector)

  "storeUserDetails" should {
    "return UserDetailsStored" when {
      "successfully stored in the backend" in {
        val testUserDetails = UserDetailsModel("qwe", "rty", LoginDetailsModel("ui", "op"))
        val testNewAccount = NewAccountModel("qwe", "rty", "ui", "op", "op")

        mockStoreUserDetails(testUserDetails)(Future.successful(UserDetailsStored))

        val result = TestService.storeUserDetails(testNewAccount)

        await(result) mustBe UserDetailsStored
      }
    }

    "expect an exception to be thrown" when {
      "the backend fails to store the details" in {
        val testUserDetails = UserDetailsModel("qwe", "rty", LoginDetailsModel("ui", "op"))
        val testNewAccount = NewAccountModel("qwe", "rty", "ui", "op", "op")

        mockStoreUserDetails(testUserDetails)(Future.failed(new Exception("qwertyuiop")))

        intercept[Exception](await(TestService.storeUserDetails(testNewAccount)))
      }
    }
  }

}
