package snd.exceptions

case class WrongInputFormatException(error: String)
  extends RuntimeException (error)
