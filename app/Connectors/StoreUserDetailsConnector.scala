package Connectors

import javax.inject.Inject
import models.UserData
import play.api.Configuration
import play.mvc.Http

import scala.concurrent.{ExecutionContext, Future}

class StoreUserDetailsConnector @Inject()(override val http: Http,
                                          val config: Configuration) {

  lazy val serviceUrl = config.get[String]("store-user-details.url")

  def storeUserDetails(firstName: String, lastName: String, email: String)

}
