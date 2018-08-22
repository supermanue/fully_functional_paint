package snd.command

import snd.exceptions.{WrongCanvasSizeException, WrongNumberOfParamsException, WrongParameterException}
import snd.model.CanvasState

import scala.util.{Failure, Success, Try}

case class CreateCanvas(xSize: Int, ySize: Int) extends Command{

  override def operate(c: CanvasState): Try[CanvasState] =
    verifiedInputParameters(xSize, ySize).map(input => {
      if ((xSize == -1) || (ySize == -1))
        createCanvas(c.xSize, c.ySize)
      else
        createCanvas(input._1, input._2)
      })

  private def verifiedInputParameters(xSize: Int, ySize: Int): Try[(Int, Int)]=
    if ((xSize == -1) && (ySize == -1))
      Success((xSize, ySize))
    else if ((xSize <= 0 ) || (ySize <= 0))
      Failure(WrongCanvasSizeException("Both dimensions must be positive"))
    else Success((xSize, ySize))

  private def createCanvas(xSize: Int, ySize: Int): CanvasState =
    CanvasState(xSize, ySize)

}



object CreateCanvas{
  def apply(inputParams: Seq[String]): Try[CreateCanvas] =
    parseInputParameters(inputParams)

  private def parseInputParameters(inputParams: Seq[String]): Try[CreateCanvas] =

    if (inputParams.length==0)
      Try(CreateCanvas(-1,-1))
    else if (inputParams.length ==2)
      Try(CreateCanvas(inputParams(0).toInt, inputParams(1).toInt))
    else
      Failure(WrongNumberOfParamsException("Wrong number of parameters for operation"))
  }
