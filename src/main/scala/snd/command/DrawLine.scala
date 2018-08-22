package snd.command

import snd.exceptions.{NotVerticalOrHorizontalLineException, WrongNumberOfParamsException, WrongParameterException}
import snd.model.{CanvasState, Point}
import snd.controller.GraphicalLibrary
import scala.util.{Failure, Success, Try}

case class DrawLine(start: Point, end: Point) extends Command{

  override def operate(canvas: CanvasState): Try[CanvasState] =
    verifiedInputParameters(start, end, canvas).map(input =>
      if (input._1.xPos == input._2.xPos)
        createHorizontalLine(input._1, input._2, canvas)
    else createVerticalLine(input._1, input._2, canvas))


  private def verifiedInputParameters(start: Point, end: Point, canvas: CanvasState): Try[(Point, Point)]=
    if (!start.isInCanvas(canvas) || !end.isInCanvas(canvas))
      Failure(WrongParameterException("One or more points do not fit inside the canvas"))
    else if (!(start.xPos == end.xPos) && !(start.yPos == end.yPos))
      Failure(NotVerticalOrHorizontalLineException("Line must be vertical or horizontal"))
    else Success(start, end)

  private def createHorizontalLine(start:Point, end:Point, canvas: CanvasState): CanvasState =
    GraphicalLibrary.createHorizontalLine(start, end, canvas)

  private def createVerticalLine(start:Point, end:Point, canvas: CanvasState):CanvasState =
    GraphicalLibrary.createVerticalLine(start, end, canvas)

}

object DrawLine{
  def apply(inputParams: Seq[String]): Try[DrawLine] =
    parseInputParameters(inputParams)

  private def parseInputParameters(inputParams: Seq[String]): Try[DrawLine] =
    if  (inputParams.length !=4)
      Failure(WrongNumberOfParamsException("Wrong number of parameters for operation"))
    else
      Try(DrawLine(Point(inputParams(0).toInt -1, inputParams(1).toInt -1),
        Point(inputParams(2).toInt -1, inputParams(3).toInt -1)))
}

