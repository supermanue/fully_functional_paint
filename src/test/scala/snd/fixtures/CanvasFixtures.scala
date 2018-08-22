package snd.fixtures

import snd.model.{CanvasState, Point}

trait CanvasFixtures {

  val baseCanvas = CanvasState(15, 10)
  val bigCanvas = CanvasState(30, 30)

  val pointInBaseCanvas = Point(8, 5)
  val pointNotInBaseCanvas = Point(20, 20)

  val otherPointInBaseCanvasVerticallAligned= Point(10,5)
  val otherPointInBaseCanvasHorizontallyAligned  = Point(8,7)

}
