package controllers

import connectors.StoreUserDetailsConnector.UserDetailsStored
import forms.NewAccountForm._
import javax.inject.{Inject, Singleton}
import play.api.i18n.I18nSupport
import play.api.mvc._
import services.StoreUserDetailsService
import views.html.create_account

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CreateAccountController @Inject()(mcc: MessagesControllerComponents,
                                        storeUserDetailsService: StoreUserDetailsService
                                       )(implicit ec: ExecutionContext) extends MessagesAbstractController(mcc) {

  def show(): Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(
        Ok(views.html.create_account(newAccountForm))
      )
  }

  def submit(): Action[AnyContent] = Action.async {
    implicit request =>
      newAccountForm.bindFromRequest().fold(
        formWithErrors => {
          Future.successful(BadRequest(create_account(formWithErrors)))
        },
        newAccountModel => {
          storeUserDetailsService.storeUserDetails(newAccountModel) map {
            case Right(UserDetailsStored) => Redirect(routes.YourDetailsController.show())
            case Left(_) => throw new Exception
          }
        }
      )

  }
}
