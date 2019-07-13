package models

sealed trait YesNoModel

case object Yes extends YesNoModel

case object No extends YesNoModel
