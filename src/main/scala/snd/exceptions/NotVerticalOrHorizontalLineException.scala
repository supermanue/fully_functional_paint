package snd.exceptions

case class NotVerticalOrHorizontalLineException(error: String)
  extends RuntimeException (error)
