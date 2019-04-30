package controllers

import forms.LoginForm
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import views.html.login
import scala.concurrent.Future

@Singleton
class LoginPageController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def show(): Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(login(LoginForm.loginForm, routes.LoginPageController.show()))
      )
  }

  def submit(): Action[AnyContent] = Action.async {
    implicit request =>
      LoginForm.loginForm.bindFromRequest().fold(
        formWithErrors => {
          Future.successful(BadRequest(login(formWithErrors, routes.LoginPageController.submit())))
        },
        successfulLogin => Future.successful(NotImplemented)
      )
  }
}
