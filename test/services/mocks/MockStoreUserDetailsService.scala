package services.mocks

import connectors.StoreUserDetailsConnector.StoreUserDetailsResponse
import models.NewAccountModel
import org.mockito.ArgumentMatchers
import org.mockito.Mockito._
import org.mockito.stubbing.OngoingStubbing
import org.scalatestplus.mockito.MockitoSugar
import services.StoreUserDetailsService

import scala.concurrent.Future

trait MockStoreUserDetailsService extends MockitoSugar {

  val mockStoreUserDetailsService: StoreUserDetailsService = mock[StoreUserDetailsService]

  def mockStoreUserDetails(newAccountModel: NewAccountModel)(response: Future[StoreUserDetailsResponse]): OngoingStubbing[Future[StoreUserDetailsResponse]] = {
    when(mockStoreUserDetailsService.storeUserDetails(
      ArgumentMatchers.eq(newAccountModel)
    )) thenReturn response
  }

}
