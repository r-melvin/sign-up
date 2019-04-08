package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import form.LoginForm.loginForm
import scala.concurrent.Future
import errors.LoginFormError


@Singleton
class LoginPageController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def show(): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(
      Ok(views.html.login(loginForm))
    )
  }

  def submit(): Action[AnyContent] = Action.async { implicit request =>
    loginForm.bindFromRequest().fold(
      formWithErrors => {
        Future.successful(BadRequest(views.html.login(formWithErrors)))
      }, successfulLogin => Future.successful(Redirect(routes.LoginPageController.show()))
    )
  }
}
