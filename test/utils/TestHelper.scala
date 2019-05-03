package utils

import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.i18n._
import play.api.inject.Injector
import play.api.mvc.{Call, RequestHeader}
import play.api.test.FakeRequest

trait TestHelper extends WordSpec with Matchers with GuiceOneAppPerSuite {
  lazy val injector: Injector = app.injector
  lazy val messagesApi: MessagesApi = injector.instanceOf[MessagesApi]
  implicit lazy val fakeRequest: RequestHeader = FakeRequest()
  lazy val errorFakeRequest = FakeRequest()
  implicit lazy val messages: Messages = messagesApi.preferred(fakeRequest)
  val testCall = Call("POST", "/test-url")
}
