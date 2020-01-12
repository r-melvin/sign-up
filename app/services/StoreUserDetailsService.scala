package services

import connectors.StoreUserDetailsConnector
import connectors.StoreUserDetailsConnector.UserDetailsStored
import javax.inject.{Inject, Singleton}
import models.{LoginDetailsModel, NewAccountModel, UserDetailsModel}

import scala.concurrent.Future

@Singleton
class StoreUserDetailsService @Inject()(storeUserDetailsConnector: StoreUserDetailsConnector) {

  def storeUserDetails(newAccountModel: NewAccountModel): Future[UserDetailsStored.type] = {

    val loginDetails = LoginDetailsModel(
      newAccountModel.email,
      newAccountModel.password
    )

    val userDetails = UserDetailsModel(
      newAccountModel.firstName,
      newAccountModel.lastName,
      loginDetails
    )

    storeUserDetailsConnector.storeUserDetails(userDetails)
  }

}
