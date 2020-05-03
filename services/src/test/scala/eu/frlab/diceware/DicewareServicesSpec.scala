package eu.frlab.diceware

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DicewareServicesSpec extends AnyFlatSpec with Matchers {

  val dicewareService = new DicewareService()

  "Generate 3, snake" should "produce a 3 word password separated with underscore" in {
    val response = dicewareService.generate(3, SnakeConcatMode.code)

    response.head.numberOfTokens should be(3)
    response.head.concatMode should be(SnakeConcatMode.code)
    val password = response.head.rolls.map(_.word).mkString("_")

    response.head.password should be(password)
  }

  "Generate 5, camel" should "produce a 5 word password camel case concatenated" in {
    val response = dicewareService.generate(5, CamelConcatMode.code)

    response.head.numberOfTokens should be(5)
    response.head.concatMode should be(CamelConcatMode.code)
    val password = response.head.rolls.map(_.word).reduceLeft(_ + _.capitalize)

    response.head.password should be(password)
  }

  "Generate 8, kebab" should "produce an 8 word password kebab case concatenated" in {
    val response = dicewareService.generate(8, KebabConcatMode.code)

    response.head.numberOfTokens should be(8)
    response.head.concatMode should be(KebabConcatMode.code)
    val password = response.head.rolls.map(_.word).mkString("-")

    response.head.password should be(password)
  }

  "Generate 2, flat" should "produce a 2 word password flatly concatenated" in {
    val response = dicewareService.generate(2, FlatConcatMode.code)

    response.head.numberOfTokens should be(2)
    response.head.concatMode should be(FlatConcatMode.code)
    response.head.rolls.map(_.word).mkString should be(response.head.password)
  }

  "Generate with require digit" should "produce a password that includes a digit" in {
    val response = dicewareService.generate(2, FlatConcatMode.code, requireDigit = true)
    val password = response.head.password

    password should include regex "[0-9]"
  }

  "Generate with require special character" should "produce a password that includes a special character" in {
    val response = dicewareService.generate(2, FlatConcatMode.code, requireSpecialChar = true)
    val password = response.head.password

    password should include regex """[~!@#$%^&*()_+=-`{}\\\[\]\|;':"<>?,./]"""
  }

  "Generate 3 passwords" should "produce a set of 3 passwords" in {
    val response = dicewareService.generate(2, FlatConcatMode.code, numberOfPasswords = 3)

    response should have size 3
  }

  "Generate with unknown concat mode" should "throw an exception" in {
    assertThrows[IllegalStateException] {
      dicewareService.generate(2, "other")
    }
  }

}
