package eu.frlab.diceware

import com.typesafe.config.{Config, ConfigFactory}
import pureconfig._
import pureconfig.generic.auto._

case class Defaults(numberOfWords: Int, concatMode: String)

object Defaults {
  final lazy val Instance = load()

  def load(): Defaults = {
    ConfigSource.fromConfig(loadConf()).load[Defaults] match {
      case Left(error) => sys.error(s"Failed to load configuration: '${error.head.description}'")
      case Right(config) => config
    }
  }

  def loadConf(): Config = ConfigFactory.load().getConfig("defaults")

}


