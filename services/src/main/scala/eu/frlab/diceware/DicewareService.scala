package eu.frlab.diceware

import javax.inject.Inject
import org.slf4j.LoggerFactory

import scala.annotation.tailrec
import scala.util.Random

case class DicewareRecord(key: String, word: String)

case class PasswordResponse(password: String, numberOfTokens: Int, concatMode: String, rolls: Seq[DicewareRecord])

class DicewareService @Inject()() {

  private val RandomGen = Random
  private val WordMap = WordListMapMaker()

  private val log = LoggerFactory.getLogger("eu.frlab")

  def generate(numberOfWords: Int,
               concatMode: String,
               requireDigit: Boolean = false,
               requireSpecialChar: Boolean = false,
               numberOfPasswords: Int = 1): Set[PasswordResponse] = {

    @tailrec
    def generatePassword(passwordResponses: Set[PasswordResponse]): Set[PasswordResponse] = {
      val records = (1 to numberOfWords).map(_ => rollForWord())
      val words = records.map(_.word)
      val password = createPassword(words, concatMode)
      val requirementsSatisfied = (!requireDigit || "[0-9]".r.findFirstMatchIn(password).isDefined) &&
        (!requireSpecialChar || """[~!@#$%^&*()_]""".r.findFirstMatchIn(password).isDefined)

      if (requirementsSatisfied) {
        val nextPasswordResponses = passwordResponses + PasswordResponse(password, numberOfWords, concatMode, records)
        if (passwordResponses.size + 1 == numberOfPasswords) nextPasswordResponses
        else generatePassword(nextPasswordResponses)
      } else generatePassword(passwordResponses)
    }

    generatePassword(Set.empty[PasswordResponse])

  }

  def wordList(): Seq[DicewareRecord] = {
    WordMap.keys.toSeq
      .sortWith(_.toInt < _.toInt)
      .map(k => DicewareRecord(k, WordMap(k)))
  }

  def modes(): Seq[ConcatMode] = Seq(FlatConcatMode, CamelConcatMode, SnakeConcatMode, KebabConcatMode)

  private def rollForWord() = {
    @tailrec
    def rollCollect(acc: String = ""): String = {
      log.debug("Rolling dice")

      def roll = (RandomGen.nextInt(6) + 1).toString

      if (acc.length < 5) rollCollect(acc + roll) else acc
    }

    val key = rollCollect()
    val word = WordMap.getOrElse(key, "No such key")
    log.debug("Looked up word")
    DicewareRecord(key, word)
  }

  private def createPassword(passwordTokens: Seq[String], concatMode: String) = concatMode match {
    case FlatConcatMode.code => passwordTokens.mkString
    case SnakeConcatMode.code => passwordTokens.mkString("_")
    case KebabConcatMode.code => passwordTokens.mkString("-")
    case CamelConcatMode.code => (passwordTokens.head +: passwordTokens.tail.map(_.capitalize)).mkString
    case _ => throw new IllegalStateException()
  }

}
