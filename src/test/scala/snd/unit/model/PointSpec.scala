package snd.unit.model

import snd.BaseTest
import snd.fixtures.CanvasFixtures

class PointSpec extends BaseTest with CanvasFixtures{
  describe ("In Point model"){
    it ("isInCanvas should return true if a point is inside a canvas"){
      pointInBaseCanvas.isInCanvas(baseCanvas) shouldBe true
    }

    it ("isInCanvas should return false if a point is inside a canvas"){
      pointNotInBaseCanvas.isInCanvas(baseCanvas) shouldBe false
    }


  }

}
