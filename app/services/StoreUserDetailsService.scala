package services

import connectors.StoreUserDetailsConnector
import connectors.StoreUserDetailsConnector.StoreUserDetailsResponse
import javax.inject.{Inject, Singleton}
import models.{LoginDetailsModel, NewAccountModel, UserDetailsModel}
import play.api.libs.json.Json

import scala.concurrent.Future

@Singleton
class StoreUserDetailsService @Inject()(idGenerationService: IdGenerationService,
                                        storeUserDetailsConnector: StoreUserDetailsConnector) {

  protected def uuid = idGenerationService.uuid

  def storeUserDetails(newAccountModel: NewAccountModel): Future[StoreUserDetailsResponse] = {

    val userDetails =
      UserDetailsModel(
        newAccountModel.firstName,
        newAccountModel.lastName,
        LoginDetailsModel(
          newAccountModel.email,
          newAccountModel.password
        )
      )

    storeUserDetailsConnector.storeUserDetails(uuid, Json.toJsObject(userDetails))
  }

}
