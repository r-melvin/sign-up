package connectors.mocks

import connectors.CheckLoginDetailsConnector
import connectors.CheckLoginDetailsConnector.CheckLoginDetailsResponse
import models.LoginDetailsModel
import org.mockito.ArgumentMatchers
import org.mockito.Mockito._
import org.mockito.stubbing.OngoingStubbing
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future

trait MockCheckLoginDetailsConnector extends MockitoSugar {

  val mockCheckLoginDetailsConnector: CheckLoginDetailsConnector = mock[CheckLoginDetailsConnector]

  def mockCheckLoginDetails(loginDetails: LoginDetailsModel)(response: Future[CheckLoginDetailsResponse]): OngoingStubbing[Future[CheckLoginDetailsResponse]] = {
    when(mockCheckLoginDetailsConnector.checkLoginDetails(
      ArgumentMatchers.eq(loginDetails)
    )) thenReturn response
  }

}
