package views.helpers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.i18n.{Messages, MessagesApi}
import play.api.mvc.RequestHeader
import play.api.test.CSRFTokenHelper._
import play.api.test.FakeRequest

trait ViewSpecHelper extends PlaySpec with GuiceOneAppPerSuite {

  implicit val request: RequestHeader = FakeRequest().withCSRFToken

  private val messagesApi: MessagesApi = app.injector.instanceOf[MessagesApi]

  implicit val messages: Messages = messagesApi.preferred(request)

}