package snd.exceptions

case class WrongNumberOfParamsException(error: String)
  extends RuntimeException (error)
