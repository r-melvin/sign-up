package controllers

import forms.NewAccount._
import models.UserData
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import play.api.data._
import play.api.i18n.I18nSupport
import views.html.create_account

import scala.concurrent.{ExecutionContext, Future}
import connectors.StoreUserDetailsConnector

@Singleton
class CreateAccountController @Inject()(mcc: MessagesControllerComponents,
                                        storeUserDetailsConnector: StoreUserDetailsConnector
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
        success => Future.successful(Ok)
      )
  }

//  def submit(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
//    val errorFunction = { formWithErrors: Form[NewAccount] =>
//      BadRequest(views.html.create_account(formWithErrors))
//    }
//
//    val successFunction = { newAccount: NewAccount =>
//      // This is the good case, where the form was successfully parsed as a Data object.
//      val newUser = UserData(firstName = newAccount.firstName, lastName = newAccount.lastName, email = newAccount.email, password = newAccount.password)
//      storeUserDetailsConnector.storeUserDetails(newUser)
//      Redirect(routes.LoginPageController.show())
//    }
//
//    val formValidationResult = NewAccount.newAccountForm.bindFromRequest
//    formValidationResult.fold(errorFunction, successFunction)
//  }
}
