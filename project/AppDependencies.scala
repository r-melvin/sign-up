import play.sbt.PlayImport.{ehcache, guice, ws}
import sbt._

object AppDependencies {

  val compile: Seq[ModuleID] = Seq(
    ehcache,
    guice,
    ws
  )

  val test: Seq[ModuleID] = Seq(
    "org.mockito" % "mockito-core" % "3.2.4" % "test",
    "org.jsoup" % "jsoup" % "1.12.1" % "test",
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % "test, it",
    "com.github.tomakehurst" % "wiremock-jre8" % "2.26.0" % "it"
  )

}
