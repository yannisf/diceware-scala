package eu.frlab.diceware

sealed abstract class ConcatMode(val code: String, val label: String, val description: Option[String] = None)

case object FlatConcatMode extends ConcatMode("flat","Flat")
case object CamelConcatMode extends ConcatMode("camel","Camel")
case object SnakeConcatMode extends ConcatMode("snake","Snake")
case object KebabConcatMode extends ConcatMode("kebab","Kebab")
case object UnknownConcatMode extends ConcatMode("unknown","Unknown")
