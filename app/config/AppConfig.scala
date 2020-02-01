package config

import javax.inject.Inject
import play.api.Configuration

class AppConfig @Inject()(config: Configuration) {

  val appName: String = config.get[String]("app.name")
  val signUpUrl: String = config.get[String]("sign-up.url")

}
