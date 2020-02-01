package stubs

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import models.UserDetailsModel
import play.api.libs.json.Json
import utils.WireMockMethods

object StoreUserDetailsStub extends WireMockMethods {

  def stubStoreUserDetails(userDetails: UserDetailsModel)(status: Int): StubMapping =
    when(
      method = POST,
      uri = "/store-user-details",
      body = Json.toJsObject(userDetails)
    ) thenReturn status

}
