package snd.unit.command

import snd.BaseTest
import snd.command.CreateCanvas
import snd.fixtures.InputFixtures
import snd.model.CanvasState

class CreateCanvasSpec extends BaseTest with InputFixtures {

  describe ("Create Canvas") {
    it ("should create new canvas operation"){
      CreateCanvas(1,1) shouldBe a [CreateCanvas]
      CreateCanvas(correctCreateCanvasInputParams).isSuccess shouldBe true
      CreateCanvas(correctCreateCanvasInputParams).get shouldBe a [CreateCanvas]
      CreateCanvas(correctCreateCanvasInputParams).get.xSize shouldBe 15
      CreateCanvas(correctCreateCanvasInputParams).get.ySize shouldBe 10
    }

    it ("should return an error if the input format is incorrect"){
      CreateCanvas(incorrectCreateCanvasInputParams).isFailure shouldBe true
      CreateCanvas(incorrectCreateCanvasInputParams2).isFailure shouldBe true
      CreateCanvas(incorrectCreateCanvasInputParams3).isFailure shouldBe true
    }

    it ("should create a new CanvasState when operated with correct input parameters"){
      CreateCanvas(correctCreateCanvasInputParams).get.operate(null).isSuccess shouldBe true
      CreateCanvas(correctCreateCanvasInputParams).get.operate(null).get shouldBe a [CanvasState]
      CreateCanvas(correctCreateCanvasInputParams).get.operate(null).get.xSize shouldBe 15
      CreateCanvas(correctCreateCanvasInputParams).get.operate(null).get.ySize shouldBe 10
      CreateCanvas(correctCreateCanvasInputParams).get.operate(null).get.surface.length shouldBe 15
      CreateCanvas(correctCreateCanvasInputParams).get.operate(null).get.surface.head.length shouldBe 10
    }

    it ("should return an error when operated with incorrect input parameters"){
      CreateCanvas(incorrectCreateCanvasInputParams4).isSuccess shouldBe true
      CreateCanvas(incorrectCreateCanvasInputParams4).get shouldBe a [CreateCanvas]
      CreateCanvas(incorrectCreateCanvasInputParams4).get.operate(null).isFailure shouldBe true
    }
  }

}
