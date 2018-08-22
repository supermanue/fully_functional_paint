package snd.model

import scala.collection.immutable.Vector

case class CanvasState(surface: Seq[Seq[Char]], xSize: Int, ySize: Int) {
  override def toString =
    " " + (0 until xSize).map(_ => "-").mkString("") + "\n" +
      (0 until ySize).map( yPos =>{
        "|" +
        (0 until xSize).map (
          xPos => surface(xPos)(yPos)
        ).mkString("") +
          "|\n"
      }).mkString("") +
      " " + (0 until xSize).map(_ => "-").mkString("") + "\n"


  def update(p: Point, value: Char) =
    CanvasState(surface.updated(p.xPos, surface(p.xPos).updated(p.yPos, value)), xSize, ySize)

}

object CanvasState{
  def apply(xSize: Int, ySize:Int): CanvasState =
  //surface is here declared as a Vector (and not as a Seq) so a different implementation can be chosen
    new CanvasState(Vector.fill(xSize, ySize)(' '), xSize, ySize)

}
