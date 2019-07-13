package connectors

import config.AppConfig
import connectors.StoreUserDetailsConnector._
import javax.inject.{Inject, Singleton}
import play.api.http.Status._
import play.api.libs.json.JsObject
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class StoreUserDetailsConnector @Inject()(config: AppConfig, ws: WSClient)(implicit ec: ExecutionContext) {

  def storeUserDetails(id: String, userDetails: JsObject): Future[StoreUserDetailsResponse] = {
    def url(id: String) = s"${config.signUpUrl}/store-user-details/$id"

    ws.url(url(id))
      .addHttpHeaders("Content-Type" -> "application/json")
      .post(userDetails)
      .map(response =>
        response.status match {
          case CREATED =>
            Right(UserDetailsStored)
          case INTERNAL_SERVER_ERROR =>
            Left(DatabaseFailure)
          case BAD_REQUEST =>
            Left(InvalidJson)
        }
      )
  }

}

object StoreUserDetailsConnector {

  type StoreUserDetailsResponse = Either[StoreUserDetailsFailure, UserDetailsStored.type]

  case object UserDetailsStored

  sealed trait StoreUserDetailsFailure

  case object DatabaseFailure extends StoreUserDetailsFailure

  case object InvalidJson extends StoreUserDetailsFailure

}
