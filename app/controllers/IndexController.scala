package controllers

import javax.inject.{Inject, Singleton}
import models.{No, Yes}
import forms.YesNoForm.yesNoForm
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class IndexController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def show(): Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(views.html.index(yesNoForm, routes.IndexController.submit()))
      )
  }

  def submit(): Action[AnyContent] = Action.async {
    implicit request =>
      yesNoForm.bindFromRequest().fold(
        formWithErrors =>
          Future.successful(
            BadRequest(views.html.index(formWithErrors, routes.IndexController.submit()))
          ),
        {
          case Yes => Future.successful(
            Redirect(routes.LoginPageController.show())
          )
          case No => Future.successful(
            Redirect(routes.CreateAccountController.show())
          )
        }
      )
  }

}
