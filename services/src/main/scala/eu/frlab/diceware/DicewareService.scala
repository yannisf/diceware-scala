package eu.frlab.diceware

import eu.frlab.diceware.ConcatMode._
import javax.inject.Inject
import org.slf4j.LoggerFactory

import scala.annotation.tailrec
import scala.util.Random

case class DicewareRecord(key: String, word: String)

case class PasswordResponse(password: String, numberOfTokens: Int, concatMode: String, rolls: Seq[DicewareRecord])

class DicewareService @Inject() () {

  private val RandomGen = Random
  private val WordMap = WordListMapMaker()

  private val log = LoggerFactory.getLogger("eu.frlab")

  def generate(numberOfWords: Int, concatMode: String): PasswordResponse = {
    val records = (1 to numberOfWords).map(_ => rollForWord())
    val words = records.map(_.word)
    val password = createPassword(words, concatMode)

    PasswordResponse(password, numberOfWords, concatMode, records)
  }

  def wordList(): Seq[DicewareRecord] = {
    WordMap.keys.toSeq
      .sortWith(_.toInt < _.toInt)
      .map(k => DicewareRecord(k, WordMap(k)))
  }

  private def rollForWord() = {
    @tailrec
    def rollCollect(acc: String = ""): String = {
      log.debug("Rolling dice")

      def roll = (RandomGen.nextInt(6) + 1).toString

      if (acc.length < 5) rollCollect(acc + roll) else acc
    }

    val key = rollCollect()
    val word = WordMap.getOrElse(key, "No such key")
    log.info("Looked up word")
    DicewareRecord(key, word)
  }

  private def createPassword(passwordTokens: Seq[String], concatMode: String) = {
    ConcatMode.toMode(concatMode) match {
      case Flat => passwordTokens.mkString
      case Snake => passwordTokens.mkString("_")
      case Kebab => passwordTokens.mkString("-")
      case Camel => (passwordTokens.head +: passwordTokens.tail.map(_.capitalize)).mkString
      case _ => throw new IllegalStateException()
    }
  }

}
