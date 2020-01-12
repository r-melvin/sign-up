package connectors

import config.AppConfig
import connectors.CheckLoginDetailsConnector._
import javax.inject.Inject
import models.LoginDetailsModel
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class CheckLoginDetailsConnector @Inject()(config: AppConfig, ws: WSClient)(implicit ec: ExecutionContext) {

  def checkLoginDetails(loginDetails: LoginDetailsModel): Future[CheckLoginDetailsResponse] = {

    ws.url(url = s"${config.signUpUrl}/check-login-details")
      .addHttpHeaders("Content-Type" -> "application/json")
      .post(Json.toJsObject(loginDetails))
      .map(
        response =>
          response.status match {
            case NO_CONTENT =>
              Right(LoginDetailsMatch)
            case FORBIDDEN =>
              Left(LoginDetailsDoNotMatch)
            case NOT_FOUND =>
              Left(LoginDetailsNotFound)
            case _ =>
              throw new Exception(s"[Sign Up Backend] Check Login Details endpoint returned status: ${response.status} with body: ${response.body}")
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

}

