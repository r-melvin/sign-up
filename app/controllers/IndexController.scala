package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import forms.ExistingAccount.existingAccountForm
import scala.concurrent.Future
import play.api.data.Form
import play.api.i18n.I18nSupport


@Singleton
class IndexController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) with I18nSupport {

  def show(): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(
      Ok(views.html.index(existingAccountForm))
    )
  }

  def submit(): Action[AnyContent] = Action.async { implicit request =>
    existingAccountForm.bindFromRequest().fold(
      formWithErrors =>{
        Future.successful(BadRequest(views.html.index(formWithErrors)))},
      success => {success.account match {
        case "Yes" => Future.successful(Redirect(routes.LoginPageController.show()))
        case _ => Future.successful(Redirect(routes.IndexController.show()))
      }
      }
    )
  }
}
