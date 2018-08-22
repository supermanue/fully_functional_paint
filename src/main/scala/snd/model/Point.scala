package snd.model

case class Point(xPos: Int, yPos: Int){
  def isInCanvas(c: CanvasState): Boolean =
    (xPos >=0) && (yPos >= 0 )&&(xPos < c.xSize) && ( yPos < c.ySize)

}

