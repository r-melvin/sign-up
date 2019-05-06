name := """sign-up"""
organization := "com.ben10"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  guice,
  ws,
"org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test,
"org.jsoup" % "jsoup" % "1.11.3" % Test,
"de.leanovate.play-mockws" %% "play-mockws" % "2.6.6" % Test,
  "org.mockito" % "mockito-core" % "2.13.0" % "test",
ehcache)

coverageMinimum := 80

coverageHighlighting := true

coverageExcludedPackages := "<empty>;Reverse.*;router\\.*"