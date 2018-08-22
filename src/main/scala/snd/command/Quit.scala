package snd.command

import snd.exceptions.{QuitApplicationException, WrongNumberOfParamsException}
import snd.model.CanvasState

import scala.util.{Failure, Success, Try}

case class Quit() extends Command{

  override def operate(canvas: CanvasState): Try[CanvasState] =
    Failure(QuitApplicationException("Exit application"))
}

object Quit{
  def apply(inputParams: Seq[String]): Try[Quit] =
    parseInputParameters(inputParams)

  private def parseInputParameters(inputParams: Seq[String]): Try[Quit] =
    if  (inputParams.nonEmpty)
      Failure(WrongNumberOfParamsException("Wrong number of parameters for operation"))
    else
      Success(Quit())
}