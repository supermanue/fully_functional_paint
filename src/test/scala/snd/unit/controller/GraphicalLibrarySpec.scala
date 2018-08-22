package snd.unit.controller

import snd.BaseTest
import snd.controller.GraphicalLibrary._
import snd.controller.InputAnalyzer
import snd.exceptions.WrongNumberOfParamsException
import snd.fixtures.CanvasFixtures
import snd.model.Point

import scala.util.Failure

class GraphicalLibrarySpec extends BaseTest with CanvasFixtures {
  describe ("getColor") {
    it ("should get the color from a given point"){
      getColor(baseCanvas, pointInBaseCanvas) shouldBe ' '
    }
  }

  describe ("createHorizontalLine") {
    it ("should draw an horizontal line bewteen the two desired points, aligned horizontally"){
      val desiredCanvas = (5 to 7).map(Point(8,_)).foldLeft(baseCanvas)((canvas,p) => canvas.update(p, 'x'))

      createHorizontalLine(pointInBaseCanvas, otherPointInBaseCanvasHorizontallyAligned,baseCanvas) shouldBe desiredCanvas
    }
     }

  describe ("createVerticalLine") {
    it ("should draw a vertical line bewteen the two desired points, aligned vertically"){

      val desiredCanvas = (8 to 10).map(Point(_,5)).foldLeft(baseCanvas)((canvas,p) => canvas.update(p, 'x'))
      createVerticalLine(pointInBaseCanvas, otherPointInBaseCanvasVerticallAligned,baseCanvas) shouldBe desiredCanvas
    }
  }



}
