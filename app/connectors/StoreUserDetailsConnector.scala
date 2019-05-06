package connectors

import config.AppConfig
import javax.inject.Inject
import models._
import models.forms.Credentials
import play.api.http.Status._
import play.api.libs.json.{JsString, JsValue, Json}
import play.api.libs.ws.{WSClient, _}

import scala.concurrent.{ExecutionContext, Future}

class StoreUserDetailsConnector @Inject()(config: AppConfig, ws: WSClient)(implicit ec: ExecutionContext) {

  def storeUserDetails(details: UserData, requestId: String): Future[Either[String, JsValue]] = {
    val url = s"${config.signUpUrl}/store-user-details"
    val request: WSRequest = ws.url(url)
    val data = Json.toJsObject(details) ++ Json.obj("requestId" -> requestId)
    request
      .addHttpHeaders("Content-Type" -> "application/json")
      .post(data)
      .map(response =>
        response.status match {
          case CREATED =>
            Right(Json.toJson(Credentials(details.email, details.password)))
          case INTERNAL_SERVER_ERROR =>
            Left("An error occurred")
        }
      )

  }

}
