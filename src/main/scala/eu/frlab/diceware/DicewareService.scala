package eu.frlab.diceware

import ConcatMode.{Camel, Flat, Snake}

import scala.util.Random

object DicewareService {

  private val RandomGen = Random
  private val WordMap = WordListMapMaker()
  private val RequiredNumberOfRolls = 5
  private val DefaultConcatMode = "camel"
  private val DefaultNumberOfTokens = 4

  def generate(numberOfTokens: Option[Int], mode: Option[String]): PasswordResponse = {
    def rollForWord() = WordMap.getOrElse(rollCollect(), "No such key")

    def rollCollect(acc: String = ""): String = if (acc.length < RequiredNumberOfRolls) rollCollect(acc + roll) else acc

    def roll: String = (RandomGen.nextInt(6) + 1).toString

    val passwordTokens = (1 to numberOfTokens.getOrElse(DefaultNumberOfTokens)).map(_ => rollForWord())

    val password: String = ConcatMode.toMode(mode.getOrElse(DefaultConcatMode)) match {
      case Flat => passwordTokens.mkString
      case Snake => passwordTokens.mkString("_")
      case Camel => (passwordTokens.head +: passwordTokens.tail.map(_.capitalize)).mkString
      case _ => throw new IllegalStateException()
    }

    PasswordResponse(password, numberOfTokens.getOrElse(DefaultNumberOfTokens), mode.getOrElse(DefaultConcatMode))
  }

  case class PasswordResponse(password: String, numberOfTokens: Int, concatMode: String)
}

