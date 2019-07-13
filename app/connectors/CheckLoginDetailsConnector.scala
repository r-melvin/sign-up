package connectors

import config.AppConfig
import connectors.CheckLoginDetailsConnector._
import javax.inject.Inject
import play.api.http.Status._
import play.api.libs.json.JsObject
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class CheckLoginDetailsConnector @Inject()(config: AppConfig, ws: WSClient)(implicit ec: ExecutionContext) {

  def checkLoginDetails(loginDetails: JsObject): Future[CheckLoginDetailsResponse] = {
    val url = s"${config.signUpUrl}/check-login-details"
    ws.url(url)
      .addHttpHeaders("Content-Type" -> "application/json")
      .post(loginDetails)
      .map(response =>
        response.status match {
          case NO_CONTENT =>
            Right(LoginDetailsMatch)
          case BAD_REQUEST =>
            Left(InvalidJson)
          case FORBIDDEN =>
            Left(LoginDetailsDoNotMatch)
          case NOT_FOUND =>
            Left(LoginDetailsNotFound)
          case INTERNAL_SERVER_ERROR =>
            Left(DatabaseFailure)
        }
      )

  }
}

object CheckLoginDetailsConnector {

  type CheckLoginDetailsResponse = Either[CheckLoginDetailsFailure, LoginDetailsMatch.type]

  case object LoginDetailsMatch

  sealed trait CheckLoginDetailsFailure

  case object LoginDetailsDoNotMatch extends CheckLoginDetailsFailure

  case object LoginDetailsNotFound extends CheckLoginDetailsFailure

  case object DatabaseFailure extends CheckLoginDetailsFailure

  case object InvalidJson extends CheckLoginDetailsFailure

}

