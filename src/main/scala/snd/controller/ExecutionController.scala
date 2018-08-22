package snd.controller

import snd.model.CanvasState
import snd.configuration.Configuration._
import snd.exceptions.QuitApplicationException

import scala.util.{Failure, Success, Try}
import scalaz.effect.IO.{putStrLn, readLn}


object ExecutionController {


  def executeApp = executeWhileNotQuit(CanvasState(0,0), "")

  private def executeWhileNotQuit (canvas: CanvasState, message: String): Unit = {

    val ioOp = for{
      _    <- putStrLn(canvas.toString)
      _    <- putStrLn (message + "enter command: ")
      line <- readLn
    } yield line

    val line = ioOp.unsafePerformIO()

    performIteration(line, canvas) match {
      case Failure(QuitApplicationException(_)) =>
      case Failure(ex) => executeWhileNotQuit(canvas, ex.toString + "\n")
      case Success(newCanvas) => executeWhileNotQuit(newCanvas, "")
    }
  }

  def performIteration(line: String, canvas:CanvasState): Try[CanvasState] =
    for {
      maybeArguments <- inputAnalyzer.splitToInput(line)
      maybeCommand <- commandProcessor.process(maybeArguments)
      maybeCanvas <- maybeCommand.operate(canvas)
    } yield maybeCanvas

}



