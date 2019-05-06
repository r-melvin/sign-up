package connectors

import config.AppConfig
import javax.inject.Inject
import models.forms.Credentials
import play.api.http.Status.{NOT_FOUND, OK}
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest}

import scala.concurrent.{ExecutionContext, Future}

class PostLoginDetailsConnector @Inject()(config: AppConfig, ws: WSClient)(implicit ec: ExecutionContext) {

  def postLoginDetails(details: Credentials): Future[Either[String, String]] = {
    val url = s"${config.signUpUrl}/check-login-details"
    val request: WSRequest = ws.url(url)
    request
      .addHttpHeaders("Content-Type" -> "application/json")
      .post(Json.toJson(details))
      .map(response =>
        response.status match {
          case OK =>
            Right("Login successful")
          case NOT_FOUND =>
            Left("Login details not found")
        }
      )

  }
}
