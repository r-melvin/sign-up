package controllers

import connectors.PostLoginDetailsConnector
import forms.LoginForm
import javax.inject.{Inject, Singleton}
import models.forms.Credentials
import play.api.mvc._
import views.html.login

import scala.concurrent.Future

@Singleton
class LoginPageController @Inject()(mcc: MessagesControllerComponents,
                                    postLoginDetailsConnector: PostLoginDetailsConnector) extends MessagesAbstractController(mcc) {

  def show(): Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(login(LoginForm.loginForm))
      )
  }

  def submit(): Action[AnyContent] = Action.async {
    implicit request =>
      LoginForm.loginForm.bindFromRequest().fold(
        formWithErrors => {
          Future.successful(BadRequest(login(formWithErrors)))
        },
        successfulLogin => {
          postLoginDetailsConnector.postLoginDetails(Credentials(successfulLogin.email, successfulLogin.password))
          Future.successful(Ok)
        }
      )
  }
}
