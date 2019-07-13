package controllers

import connectors.CheckLoginDetailsConnector.{LoginDetailsDoNotMatch, LoginDetailsMatch, LoginDetailsNotFound}
import forms.LoginForm
import javax.inject.{Inject, Singleton}
import play.api.i18n.I18nSupport
import play.api.mvc._
import services.CheckLoginDetailsService
import views.html.login

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class LoginPageController @Inject()(mcc: MessagesControllerComponents,
                                    checkLoginDetailsService: CheckLoginDetailsService
                                   )(implicit ec: ExecutionContext) extends MessagesAbstractController(mcc) with I18nSupport {

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
        loginDetailsModel => {
          checkLoginDetailsService.checkLoginDetails(loginDetailsModel) map {
            case Right(LoginDetailsMatch) => Redirect(routes.YourDetailsController.show())
            case Left(LoginDetailsDoNotMatch) => NotImplemented
            case Left(LoginDetailsNotFound) => NotImplemented
            case Left(_) => throw new Exception
          }
        }
      )
  }
}
