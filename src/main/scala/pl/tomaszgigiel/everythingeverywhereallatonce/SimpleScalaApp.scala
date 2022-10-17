package pl.tomaszgigiel.everythingeverywhereallatonce;

import cats.effect.IOApp
import com.typesafe.scalalogging.Logger

@main
def SimpleScalaApp(): Unit =
  val logger = Logger("SimpleScalaApp")
  logger.info("SimpleScalaApp: ok")
