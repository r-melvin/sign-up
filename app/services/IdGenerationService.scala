package services

import java.util.UUID

import javax.inject.{Inject, Singleton}

@Singleton
class IdGenerationService @Inject()() {

  def uuid: String = UUID.randomUUID().toString

}

