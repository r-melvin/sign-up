package services

import config.AppConfig
import connectors.GetRequestIdConnector
import javax.inject.Inject
import play.api.mvc.Request

import scala.concurrent.ExecutionContext

class SessionService @Inject()(config: AppConfig,
                               requestIdConnector: GetRequestIdConnector,
                               implicit val ec: ExecutionContext) {

  def getGeneratedRequestId() = {
    requestIdConnector.getRequestId() map {
      case Right(response) => (response \ "requestId").get
      case Left(_) => throw new Exception
    }
  }

  def retrieveRequestIdFromSession(request: Request[_]): String = {
    request.session.get("requestId").getOrElse(throw new Exception)
  }

}
