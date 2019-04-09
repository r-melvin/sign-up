package models

sealed trait YesNo

case object Yes extends YesNo

case object No extends YesNo
