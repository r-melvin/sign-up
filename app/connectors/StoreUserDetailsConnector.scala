package connectors

import config.AppConfig
import connectors.StoreUserDetailsConnector.UserDetailsStored
import javax.inject.{Inject, Singleton}
import models.UserDetailsModel
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class StoreUserDetailsConnector @Inject()(config: AppConfig, ws: WSClient)(implicit ec: ExecutionContext) {

  def storeUserDetails(userDetails: UserDetailsModel): Future[UserDetailsStored.type] = {

    ws.url(s"${config.signUpUrl}/store-user-details")
      .addHttpHeaders("Content-Type" -> "application/json")
      .post(Json.toJsObject(userDetails))
      .map(
        response =>
          response.status match {
            case CREATED =>
              UserDetailsStored
            case _ =>
              throw new Exception(s"[Sign Up Backend] Store User Details endpoint returned status: ${response.status} with body: ${response.body}")
          }
      )

  }

}

object StoreUserDetailsConnector {

  case object UserDetailsStored

}

