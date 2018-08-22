package snd.exceptions

case class WrongParameterException(error: String)
  extends RuntimeException (error)
