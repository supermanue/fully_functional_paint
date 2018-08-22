package snd.command

import snd.controller.GraphicalLibrary
import snd.exceptions.{WrongNumberOfParamsException, WrongParameterException}
import snd.model.{CanvasState, Point}

import scala.util.{Failure, Success, Try}

case class DrawRectangle(start: Point, end: Point) extends Command{

  override def operate(canvas: CanvasState): Try[CanvasState] =
    verifiedInputParameters(start, end, canvas).map(input =>
        createRectangle(input._1, input._2, canvas))

  private def verifiedInputParameters(start: Point, end: Point, canvas: CanvasState): Try[(Point, Point)]=
    if (!start.isInCanvas(canvas) || !end.isInCanvas(canvas))
      Failure(WrongParameterException("One or more points do not fit inside the canvas"))
    else if ((start.xPos > end.xPos)  || (start.yPos > end.yPos))
      Failure(WrongParameterException("First point must be on the top left, second on the bottom right"))
    else Success(start, end)

  private def createRectangle(start:Point, end:Point, canvas: CanvasState): CanvasState =
    GraphicalLibrary.createHorizontalLine(start, Point(start.xPos, end.yPos),
      GraphicalLibrary.createVerticalLine(Point(start.xPos, end.yPos), end,
        GraphicalLibrary.createHorizontalLine(end, Point(end.xPos, start.yPos),
          GraphicalLibrary.createVerticalLine(Point(end.xPos, start.yPos), start, canvas))))
}

object DrawRectangle{
  def apply(inputParams: Seq[String]): Try[DrawRectangle] =
    parseInputParameters(inputParams)

  private def parseInputParameters(inputParams: Seq[String]): Try[DrawRectangle] =
    if  (inputParams.length !=4)
      Failure(WrongNumberOfParamsException("Wrong number of parameters for operation"))
    else
      Try(DrawRectangle(Point(inputParams(0).toInt-1, inputParams(1).toInt -1),
        Point(inputParams(2).toInt -1, inputParams(3).toInt -1)))
}

