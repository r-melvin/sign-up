package object forms {

  val userIdLegalCharactersRegex = "[a-zA-Z0-9\\s]*"
  val passwordLegalCharactersRegex = "[a-zA-Z0-9]*"
  val containsOneLetterAndOneCharRegex = "(?=.*[0-9]+)(?=.*[A-Za-z]+).+"

  private[forms] val stripSpaces: String => String = _.replace(" ", "")
  private[forms] val trimPassword: String => String = _.trim

}
