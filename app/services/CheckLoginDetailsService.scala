package services

import connectors.CheckLoginDetailsConnector
import connectors.CheckLoginDetailsConnector.CheckLoginDetailsResponse
import javax.inject.{Inject, Singleton}
import models.LoginDetailsModel

import scala.concurrent.Future

@Singleton
class CheckLoginDetailsService @Inject()(checkLoginDetailsConnector: CheckLoginDetailsConnector) {

  def checkLoginDetails(loginDetails: LoginDetailsModel): Future[CheckLoginDetailsResponse] =
    checkLoginDetailsConnector.checkLoginDetails(loginDetails)

}
