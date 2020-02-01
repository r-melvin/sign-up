package utils

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.api.test.Helpers._
import play.api.{Application, Environment}

trait ComponentSpecHelper
  extends PlaySpec
    with CustomMatchers
    with WiremockHelper
    with GuiceOneServerPerSuite
    with BeforeAndAfterAll
    with BeforeAndAfterEach {

  lazy val ws: WSClient = app.injector.instanceOf[WSClient]

  override implicit lazy val app: Application =
    new GuiceApplicationBuilder()
      .in(Environment.simple())
      .configure(config)
      .build

  val mockHost: String = WiremockHelper.wiremockHost
  val mockPort: String = WiremockHelper.wiremockPort.toString
  val mockUrl: String = s"http://$mockHost:$mockPort"

  val config: Map[String, String] = Map(
    "play.filters.csrf.header.bypassHeaders.Csrf-Token" -> "nocheck",
    "sign-up.url" -> mockUrl
  )

  override def beforeAll(): Unit = {
    super.beforeAll()
    startWiremock()
  }

  override def afterAll(): Unit = {
    super.afterAll()
    stopWiremock()
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    resetWiremock()
  }

  def get(uri: String): WSResponse = {
    await(buildClient(uri).get)
  }

  def post(uri: String)(form: (String, String)*): WSResponse = {
    val formBody = (form map { case (k, v) => (k, Seq(v)) }).toMap

    await(buildClient(uri).withHttpHeaders("Csrf-Token" -> "nocheck").post(formBody))
  }

  def buildClient(path: String): WSRequest = ws.url(s"http://localhost:$port$path").withFollowRedirects(false)

}
