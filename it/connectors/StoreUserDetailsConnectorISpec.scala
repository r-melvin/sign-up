package connectors

import connectors.StoreUserDetailsConnector.UserDetailsStored
import models.{LoginDetailsModel, UserDetailsModel}
import play.api.test.Helpers._
import stubs.StoreUserDetailsStub.stubStoreUserDetails
import utils.ComponentSpecHelper

class StoreUserDetailsConnectorISpec extends ComponentSpecHelper {

  "storeUserDetails" should {
    "return UserDetailsStored" when {
      "the data is successfully stored" in {
        val testUserDetails = UserDetailsModel("12345", "67890", LoginDetailsModel("asdfghjkl@qwedrtyuiop.com", "p2ssword"))

        stubStoreUserDetails(testUserDetails)(CREATED)

        val connector = app.injector.instanceOf[StoreUserDetailsConnector]

        val res = await(connector.storeUserDetails(testUserDetails))

        res mustBe UserDetailsStored
      }
    }
  }

}
