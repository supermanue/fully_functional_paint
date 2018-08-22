package snd.controller

import snd.model.{CanvasState, Point}

object GraphicalLibrary {
  def getColor(c: CanvasState, point: Point): Char = c.surface(point.xPos)(point.yPos)

  def createHorizontalLine(start:Point, end:Point, canvas: CanvasState): CanvasState =
    (math.min(start.yPos, end.yPos) to math.max(start.yPos, end.yPos)).
      foldLeft(canvas)((c, pos) => c.update(Point(start.xPos, pos), 'x'))

  def createVerticalLine(start:Point, end:Point, canvas: CanvasState):CanvasState =
    (math.min(start.xPos, end.xPos) to math.max(start.xPos, end.xPos)).
      foldLeft(canvas)((c, pos) => c.update(Point(pos, start.yPos), 'x'))

 }
