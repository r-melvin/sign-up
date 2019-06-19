package controllers

import connectors.StoreUserDetailsConnector
import forms.NewAccountForm._
import javax.inject.{Inject, Singleton}
import models.UserData
import play.api.i18n.I18nSupport
import play.api.mvc._
import views.html.create_account
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CreateAccountController @Inject()(mcc: MessagesControllerComponents,
                                        storeUserDetailsConnector: StoreUserDetailsConnector,
                                       )(implicit ec: ExecutionContext) extends MessagesAbstractController(mcc) with I18nSupport {

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
        success => {
          storeUserDetailsConnector.storeUserDetails(UserData(
            success.firstName,
            success.lastName,
            success.email,
            success.password
          ))
          Future.successful(Redirect(routes.YourDetailsController.show))
        }
      )

  }
}
