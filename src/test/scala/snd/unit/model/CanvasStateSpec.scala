package snd.unit.model

import snd.BaseTest
import snd.fixtures.CanvasFixtures
import snd.model.CanvasState

class CanvasStateSpec extends BaseTest with CanvasFixtures{
  describe ("In CanvasState model"){
    it ("apply should create a canvas of the desired size"){
      val canvas = CanvasState(15,10)

      canvas.xSize shouldBe 15
      canvas.ySize shouldBe 10
      canvas.surface should have length 15
      canvas.surface.head  should have length 10
    }
    it ("update should set a desired color on a given point"){
      baseCanvas.update(pointInBaseCanvas, 'c').surface(pointInBaseCanvas.xPos)(pointInBaseCanvas.yPos) shouldBe 'c'
    }
  }

}
