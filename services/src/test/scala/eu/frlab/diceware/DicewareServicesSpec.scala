package eu.frlab.diceware

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DicewareServicesSpec extends AnyFlatSpec with Matchers {

  val dicewareService = new DicewareService()

  "Generate 3, snake" should "produce a 3 word password separated with underscore" in {
    val response = dicewareService.generate(3, SnakeConcatMode.code)

    response.numberOfTokens should be(3)
    response.concatMode should be(SnakeConcatMode.code)
    response.password.split("_").length should be(3)
  }

  "Generate 5, camel" should "produce a 5 word password camel case concatenated" in {
    val response = dicewareService.generate(5, CamelConcatMode.code)

    response.numberOfTokens should be(5)
    response.concatMode should be(CamelConcatMode.code)
    response.password.replaceAll("([A-Z])", " $1").split(" ").length should be(5)
  }

  "Generate 2, flat" should "produce a 2 word password flatly concatenated" in {
    val response = dicewareService.generate(2, FlatConcatMode.code)

    response.numberOfTokens should be(2)
    response.concatMode should be(FlatConcatMode.code)
    response.rolls.map(_.word).mkString should be(response.password)
  }

  "Generate with unknown concat mode" should "throw an exception" in {
    assertThrows[IllegalStateException] {
      dicewareService.generate(2, "other")
    }
  }

}
