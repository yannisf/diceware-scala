package eu.frlab.diceware

import org.slf4j.LoggerFactory

import scala.io.Source

/**
  * Generates the Diceware dictionary.
  */
object WordListMapMaker {

  private val log = LoggerFactory.getLogger("eu.frlab")

  private val Filename = "wordlist.txt"

  def apply(): Map[String, String] = {
    log.info("Initializing Diceware dictionary")
    val source = Source.fromResource(Filename)
    val wordlist = source.getLines
      .map(_.split("""\s+"""))
      .map(a => a(0).trim -> a(1).trim)
      .toMap
    source.close()
    wordlist
  }

}
