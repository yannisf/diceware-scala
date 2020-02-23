package eu.frlab.diceware

import org.scalatest._

class DicewareServicesSpec extends FlatSpec with Matchers {

  val dicewareService = new DicewareService()

  "Generate 3, snake" should "produce a 3 word password separated with underscore" in {
    val response = dicewareService.generate(3, ConcatMode.Snake.toString)

    response.numberOfTokens should be(3)
    response.concatMode should be(ConcatMode.Snake.toString)
    response.password.split("_").length should be(3)
  }

  "Generate 5, camel" should "produce a 5 word password camel case concatenated" in {
    val response = dicewareService.generate(5, ConcatMode.Camel.toString)

    response.numberOfTokens should be(5)
    response.concatMode should be(ConcatMode.Camel.toString)
    response.password.replaceAll("([A-Z])", " $1").split(" ").length should be(5)
  }

  "Generate 2, flat" should "produce a 2 word password flatly concatenated" in {
    val response = dicewareService.generate(2, ConcatMode.Flat.toString)

    response.numberOfTokens should be(2)
    response.concatMode should be(ConcatMode.Flat.toString)
    response.rolls.map(_.word).mkString should be(response.password)
  }

  "Generate with unknown concat mode" should "throw an exception" in {
    assertThrows[IllegalStateException] {
      dicewareService.generate(2, "other")
    }
  }

}