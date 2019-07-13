package services.mocks

import connectors.CheckLoginDetailsConnector.CheckLoginDetailsResponse
import models.LoginDetailsModel
import org.mockito.ArgumentMatchers
import org.mockito.Mockito._
import org.mockito.stubbing.OngoingStubbing
import org.scalatestplus.mockito.MockitoSugar
import services.CheckLoginDetailsService

import scala.concurrent.Future

trait MockCheckLoginDetailsService extends MockitoSugar {

  val mockCheckLoginDetailsService: CheckLoginDetailsService = mock[CheckLoginDetailsService]

  def mockCheckLoginDetails(loginDetailsModel: LoginDetailsModel)(response: Future[CheckLoginDetailsResponse]): OngoingStubbing[Future[CheckLoginDetailsResponse]] = {
    when(mockCheckLoginDetailsService.checkLoginDetails(
      ArgumentMatchers.eq(loginDetailsModel)
    )) thenReturn response
  }

}
