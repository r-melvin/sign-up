package services

import connectors.CheckLoginDetailsConnector
import connectors.CheckLoginDetailsConnector.CheckLoginDetailsResponse
import javax.inject.{Inject, Singleton}
import models.LoginDetailsModel
import play.api.libs.json.Json

import scala.concurrent.Future

@Singleton
class CheckLoginDetailsService @Inject()(checkLoginDetailsConnector: CheckLoginDetailsConnector) {

  def checkLoginDetails(loginDetailsModel: LoginDetailsModel): Future[CheckLoginDetailsResponse] = {
    val loginDetails = Json.toJsObject(loginDetailsModel)
    checkLoginDetailsConnector.checkLoginDetails(loginDetails)
  }

}
