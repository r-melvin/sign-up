package errors

import models.forms.{ExistingAccount, Login}
import play.api.data.Form

sealed trait Errors

final case class ExistingAccountFormError(formErrors:Form[ExistingAccount]) extends Errors
final case class LoginFormError(formErrors:Form[Login]) extends Errors
