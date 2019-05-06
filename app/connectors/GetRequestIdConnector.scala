package connectors

import config.AppConfig
import javax.inject.Inject
import play.api.http.Status._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSClient, WSRequest}

import scala.concurrent.{ExecutionContext, Future}

class GetRequestIdConnector @Inject()(config: AppConfig, ws: WSClient)(implicit ec: ExecutionContext) {

  def getRequestId(): Future[Either[String, JsValue]] = {
    val url = s"${config.signUpUrl}/get-request-id"
    val request: WSRequest = ws.url(url)
    request
      .get
      .map(response =>
        response.status match {
          case OK =>
            Right(Json.toJson(response.body))
          case INTERNAL_SERVER_ERROR =>
            Left("An error occurred")
        }
      )
  }
}
