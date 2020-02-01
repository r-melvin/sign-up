package stubs

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import models.LoginDetailsModel
import play.api.libs.json.Json
import utils.WireMockMethods

object CheckLoginDetailsStub extends WireMockMethods {

  def stubCheckLoginDetails(loginDetails: LoginDetailsModel)(status: Int): StubMapping =
    when(
      method = POST,
      uri = "/check-login-details",
      body = Json.toJsObject(loginDetails)
    ) thenReturn status

}
