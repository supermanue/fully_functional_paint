package snd.exceptions

case class WrongCanvasSizeException(error: String)
  extends RuntimeException (error)
