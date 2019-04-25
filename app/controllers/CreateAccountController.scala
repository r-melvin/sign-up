package controllers

import forms.NewAccount
import models.{Credentials, UserData}
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import play.api.data._
import play.api.i18n.I18nSupport

import scala.concurrent.{ExecutionContext, Future}
import connectors.StoreUserDetailsConnector

@Singleton
class CreateAccountController @Inject()(mcc: MessagesControllerComponents,
                                        storeUserDetailsConnector: StoreUserDetailsConnector
                                       )(implicit ec: ExecutionContext) extends MessagesAbstractController(mcc) with I18nSupport {


  private val credentials = scala.collection.mutable.ArrayBuffer(
    Credentials("example@example.com", "p2ssword")
  )

  def show() = Action { implicit request =>
      Ok(views.html.create_account(NewAccount.newAccountForm))
  }

  def submit() = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[NewAccount] =>
      BadRequest(views.html.create_account(formWithErrors))
    }

    val successFunction = { newAccount: NewAccount =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val newUser = UserData(firstName = newAccount.firstName, lastName = newAccount.lastName, email = newAccount.email, password = newAccount.password)
      storeUserDetailsConnector.storeUserDetails(newUser)
      Redirect(routes.LoginPageController.show())
    }

    val formValidationResult = NewAccount.newAccountForm.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }
}
