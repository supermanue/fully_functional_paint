package snd.integration

import snd.BaseTest
import snd.command.{DrawLine, DrawRectangle}
import snd.fixtures.{CanvasFixtures, InputFixtures}
import snd.configuration
import snd.controller.ExecutionController
import snd.model.CanvasState

class IntegrationTest extends BaseTest with InputFixtures with CanvasFixtures {

  describe ("SNDApplication") {
    it ("should generate a canvas of the desired size"){
      val newCanvas = ExecutionController.performIteration(correctCreateCanvasInput, null)

      newCanvas.isSuccess shouldBe true
      newCanvas.get shouldBe
      CanvasState(newCanvas.get.xSize, newCanvas.get.ySize)

    }

    it ("should execute a user command correctly"){
      val newCanvas = ExecutionController.performIteration(correctCreateCanvasInput, null)
      val canvasWithLine = ExecutionController.performIteration(correctDrawLineInput, newCanvas.get)
      val otherCanvasWithLine = DrawLine(correctDrawLineInputHorizontal).get.operate(baseCanvas)

      canvasWithLine.isSuccess shouldBe true
      canvasWithLine.get shouldBe otherCanvasWithLine.get

    }

    it ("should return an error if the user command is not correct"){
      ExecutionController.performIteration(tooLongCreateCanvasInput, null).isFailure shouldBe true
    }

    it ("should return an error after a correct Quit user command"){
      ExecutionController.performIteration(correctQuitCommand, null).isFailure shouldBe true
    }



  }



}
