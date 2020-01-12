package controllers

import forms.YesNoForm.yesNoForm
import javax.inject.{Inject, Singleton}
import models.{No, Yes}
import play.api.mvc.{Action, AnyContent, MessagesBaseController, MessagesControllerComponents}

import scala.concurrent.Future

@Singleton
class YourDetailsController @Inject()(val controllerComponents: MessagesControllerComponents) extends MessagesBaseController {

  val show: Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(views.html.your_details(yesNoForm))
      )
  }

  val submit: Action[AnyContent] = Action.async {
    implicit request =>
      yesNoForm.bindFromRequest().fold(
        formWithErrors =>
          Future.successful(
            BadRequest(views.html.your_details(formWithErrors))
          ), {
          case Yes =>
            Future.successful(
              Redirect(routes.LoginController.show())
            )
          case No =>
            Future.successful(
              Redirect(routes.CreateAccountController.show())
            )
        }
      )
  }

}
