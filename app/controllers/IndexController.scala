package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class IndexController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def show: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(
      Ok(views.html.index())
    )
  }

  def submit: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(
      NotImplemented
    )
  }

}
