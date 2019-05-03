package config

import com.typesafe.config.{Config, ConfigFactory}

class AppConfig {
  val config = ConfigFactory.load()
  val appName = config.getString("app.name")
  val signUpUrl = config.getString("sign-up.url")
}
