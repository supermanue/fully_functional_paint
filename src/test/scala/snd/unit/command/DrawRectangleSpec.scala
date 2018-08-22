package snd.unit.command

import snd.BaseTest
import snd.command.DrawRectangle
import snd.controller.GraphicalLibrary
import snd.fixtures.{CanvasFixtures, InputFixtures}
import snd.model.{CanvasState, Point}

class DrawRectangleSpec extends BaseTest with InputFixtures with CanvasFixtures {

  describe ("DrawRectangle") {
    it ("should create new DrawRectangle operation"){
      DrawRectangle(pointInBaseCanvas, otherPointInBaseCanvasHorizontallyAligned) shouldBe a [DrawRectangle]
      DrawRectangle(correctDrawRectangleInput).isSuccess shouldBe true
      DrawRectangle(correctDrawRectangleInput).get shouldBe a [DrawRectangle]
    }

    it ("should return an error if the input format is incorrect"){
      DrawRectangle(drawRectangleWrongChar).isFailure shouldBe true
      DrawRectangle(drawRectangleNonIntegerPoints).isFailure shouldBe true
    }

    it ("should create a new CanvasState with a drawn rectangle when operated with correct input parameters in a canvas of the correct size"){
      DrawRectangle(correctDrawRectangleInput).get.operate(bigCanvas).isSuccess shouldBe true
      DrawRectangle(correctDrawRectangleInput).get.operate(bigCanvas).get shouldBe a [CanvasState]
      DrawRectangle(correctDrawRectangleInput).get.operate(bigCanvas).get shouldBe
        GraphicalLibrary.createHorizontalLine(correctDrawRectanglePoint1, Point(correctDrawRectanglePoint1.xPos, correctDrawRectanglePoint2.yPos),
          GraphicalLibrary.createVerticalLine(Point(correctDrawRectanglePoint1.xPos, correctDrawRectanglePoint2.yPos), correctDrawRectanglePoint2,
            GraphicalLibrary.createHorizontalLine(correctDrawRectanglePoint2, Point(correctDrawRectanglePoint2.xPos, correctDrawRectanglePoint1.yPos),
              GraphicalLibrary.createVerticalLine(Point(correctDrawRectanglePoint2.xPos, correctDrawRectanglePoint1.yPos), correctDrawRectanglePoint1, bigCanvas))))
    }

    it ("should create a new CanvasState with a drawn horizontal if the two input points are horizontally aligned"){
      DrawRectangle(correctDrawRectangleInputHorizontal).get.operate(bigCanvas).isSuccess shouldBe true
      DrawRectangle(correctDrawRectangleInputHorizontal).get.operate(bigCanvas).get shouldBe a [CanvasState]
      DrawRectangle(correctDrawRectangleInputHorizontal).get.operate(bigCanvas).get shouldBe
        GraphicalLibrary.createHorizontalLine(correctHorizontalPoint1, correctHorizontalPoint2, bigCanvas)
    }

    it ("should return an error if any of the input points are outside the canvas"){
      DrawRectangle(correctDrawRectangleInputBig).get.operate(baseCanvas).isFailure shouldBe true
    }

  }

}
