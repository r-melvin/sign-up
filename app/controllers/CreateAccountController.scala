package controllers

import forms.NewAccountForm._
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.StoreUserDetailsService
import views.html.create_account

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CreateAccountController @Inject()(val controllerComponents: MessagesControllerComponents,
                                        storeUserDetailsService: StoreUserDetailsService
                                       )(implicit ec: ExecutionContext) extends MessagesBaseController {

  val show: Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(views.html.create_account(newAccountForm))
      )
  }

  val submit: Action[AnyContent] = Action.async {
    implicit request =>
      newAccountForm.bindFromRequest().fold(
        formWithErrors => {
          Future.successful(BadRequest(create_account(formWithErrors)))
        },
        newAccountModel =>
          storeUserDetailsService.storeUserDetails(newAccountModel).map{
            _ => Redirect(routes.YourDetailsController.show())
          }

      )

  }
}
