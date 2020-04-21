package eu.frlab.diceware

import org.scalatest._

class DicewareServerSpec extends FlatSpec with Matchers {

  "Configuration" should "be initializable" in {
    val defaults = Defaults.load()
    defaults.numberOfWords should be(4)
    defaults.concatMode should be(FlatConcatMode.code)
  }

}
