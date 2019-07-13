package controllers

import forms.YesNoForm.yesNoForm
import javax.inject.{Inject, Singleton}
import models.{No, Yes}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import scala.concurrent.Future

@Singleton
class DoYouHaveAnAccountController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def show(): Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(views.html.do_you_have_an_account(yesNoForm))
      )
  }

  def submit(): Action[AnyContent] = Action.async {
    implicit request =>
      yesNoForm.bindFromRequest().fold(
        formWithErrors =>
          Future.successful(
            BadRequest(views.html.do_you_have_an_account(formWithErrors))
          ),
        {
          case Yes =>
            Future.successful(
              Redirect(routes.LoginPageController.show())
            )
          case No =>
            Future.successful(
              Redirect(routes.CreateAccountController.show())
            )
        }
      )
  }

}
