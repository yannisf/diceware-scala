package eu.frlab.diceware

object ConcatMode extends Enumeration {
  val Flat, Camel, Snake, Unmapped = Value

  def toMode(description: String): ConcatMode.Value = description.toLowerCase match {
    case "flat" => Flat
    case "camel" => Camel
    case "snake" => Snake
    case _ => Unmapped
  }

}
