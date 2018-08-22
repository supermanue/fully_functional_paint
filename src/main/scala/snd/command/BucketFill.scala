package snd.command

import snd.controller.GraphicalLibrary
import snd.exceptions.{WrongNumberOfParamsException, WrongParameterException}
import snd.model.{CanvasState, Point}
import snd.command.utilsForLists._
import scala.util.{Failure, Success, Try}

case class BucketFill(point: Point, elem: Char) extends Command{

  override def operate(canvas: CanvasState): Try[CanvasState] =
    verifiedInputParameters(point, canvas).map(input =>
      bucketFill(input, GraphicalLibrary.getColor(canvas,input), elem, canvas))

  private def verifiedInputParameters(point: Point, canvas: CanvasState): Try[Point]=
    if (!point.isInCanvas(canvas))
      Failure(WrongParameterException("One or more points do not fit inside the canvas"))
    else Success(point)

  private def bucketFill(point:Point, oldElem: Char, newElem:Char, canvas: CanvasState): CanvasState =
    if ((GraphicalLibrary.getColor(canvas,point) != oldElem) ||
      (GraphicalLibrary.getColor(canvas,point) == newElem)) canvas
    else
      List[Point]().
        maybeAdd(point.xPos>0, Point(point.xPos -1, point.yPos)).
        maybeAdd(point.xPos < canvas.xSize -1, Point(point.xPos +1, point.yPos)).
        maybeAdd(point.yPos > 0, Point(point.xPos, point.yPos -1)).
        maybeAdd(point.yPos < canvas.ySize -1, Point(point.xPos, point.yPos +1)).
        foldLeft(
          canvas.update(point, elem))(
          (c, p) => bucketFill(p, oldElem, newElem, c))

}

object BucketFill{
  def apply(inputParams: Seq[String]): Try[BucketFill] =
    parseInputParameters(inputParams)

  private def parseInputParameters(inputParams: Seq[String]): Try[BucketFill] =
    if  (inputParams.length !=3)
      Failure(WrongNumberOfParamsException("Wrong number of parameters for operation"))
    else
      Try(BucketFill(Point(inputParams(0).toInt -1, inputParams(1).toInt -1), inputParams(2).charAt(0)))
}

object utilsForLists{
   implicit class CompanionForLists[A](l: List[A]){
     def maybeAdd(condition: Boolean, element: A): List[A] =
      if (condition) element :: l
      else l
  }

}