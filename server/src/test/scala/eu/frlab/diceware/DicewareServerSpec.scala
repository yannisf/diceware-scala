package eu.frlab.diceware

import org.scalatest._
import org.scalatest.matchers.should._
import org.scalatest.flatspec.AnyFlatSpec

class DicewareServerSpec extends AnyFlatSpec with Matchers {

  "Configuration" should "be initializable" in {
    val defaults = Defaults.load()
    defaults.numberOfWords should be(4)
    defaults.concatMode should be(FlatConcatMode.code)
  }

}
