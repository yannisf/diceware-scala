package eu.frlab.diceware

object ConcatMode extends Enumeration {
  val Flat, Camel, Snake = Value

  def toMode(description: String): ConcatMode.Value = description.toLowerCase match {
    case "flat" => Flat
    case "camel" => Camel
    case "snake" => Snake
  }

  def toDescription(mode: ConcatMode.Value): String = mode match {
    case Flat => "flat"
    case Camel => "camel"
    case Snake => "snake"
  }

}
