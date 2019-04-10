package controllers

import forms.Login
import models.User
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import play.api.data._
import play.api.i18n.I18nSupport
import scala.concurrent.Future

@Singleton
class CreateAccountController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) with I18nSupport {

  private val users = scala.collection.mutable.ArrayBuffer(
    User("exmple@example.com", "p2ssword")
  )

  def show() = Action { implicit request =>
      Ok(views.html.create_account(Login.loginForm))
  }

  def submit() = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Login] =>
      BadRequest(views.html.create_account(formWithErrors))
    }

    val successFunction = { login: Login =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val newUser = User(email = login.email, password = login.password)
      users.append(newUser)
      Redirect(routes.LoginPageController.show())
    }

    val formValidationResult = Login.loginForm.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }
}
