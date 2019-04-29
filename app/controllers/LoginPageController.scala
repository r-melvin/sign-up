package controllers

import forms.Login
import javax.inject.{Inject, Singleton}
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class LoginPageController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def show(): Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(views.html.login(Login.loginForm))
      )
  }

  def submit(): Action[AnyContent] = Action.async {
    implicit request =>
      Login.loginForm.bindFromRequest().fold(
        formWithErrors => {
          Future.successful(BadRequest(views.html.login(formWithErrors)))
        }, successfulLogin => Future.successful(Redirect(routes.LoginPageController.show()))
      )
  }
}
