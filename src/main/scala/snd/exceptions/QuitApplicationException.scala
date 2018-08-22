package snd.exceptions

case class QuitApplicationException(error: String)
  extends RuntimeException (error)
