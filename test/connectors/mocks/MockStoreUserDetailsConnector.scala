package connectors.mocks

import connectors.StoreUserDetailsConnector
import connectors.StoreUserDetailsConnector.UserDetailsStored
import models.UserDetailsModel
import org.mockito.ArgumentMatchers
import org.mockito.Mockito._
import org.mockito.stubbing.OngoingStubbing
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future

trait MockStoreUserDetailsConnector extends MockitoSugar {

  val mockStoreUserDetailsConnector: StoreUserDetailsConnector = mock[StoreUserDetailsConnector]

  def mockStoreUserDetails(userDetails: UserDetailsModel)(response: Future[UserDetailsStored.type]): OngoingStubbing[Future[UserDetailsStored.type]] = {
    when(mockStoreUserDetailsConnector.storeUserDetails(
      ArgumentMatchers.eq(userDetails)
    )) thenReturn response
  }

}
