package snd.unit.command

import snd.BaseTest
import snd.command.DrawLine
import snd.fixtures.{CanvasFixtures, InputFixtures}
import snd.model.{CanvasState, Point}

class DrawLineSpec extends BaseTest with InputFixtures with CanvasFixtures {

  describe ("DrawLine") {
    it ("should create new DrawLine operation"){
      DrawLine(pointInBaseCanvas, otherPointInBaseCanvasHorizontallyAligned) shouldBe a [DrawLine]
      DrawLine(correctDrawLineInputHorizontal).isSuccess shouldBe true
      DrawLine(correctDrawLineInputHorizontal).get shouldBe a [DrawLine]
      DrawLine(correctDrawLineInputHorizontal).get.start shouldBe correctHorizontalPoint1
      DrawLine(correctDrawLineInputHorizontal).get.end shouldBe correctHorizontalPoint2
    }

    it ("should return an error if the input format is incorrect"){
      DrawLine(drawLineWrongChar).isFailure shouldBe true
      DrawLine(drawLineNonIntegerPoints).isFailure shouldBe true
    }

    it ("should create a new CanvasState with a drawn horizontal line when operated with correct input parameters in a canvas of the correct size"){
      DrawLine(correctDrawLineInputHorizontal).get.operate(bigCanvas).isSuccess shouldBe true
      DrawLine(correctDrawLineInputHorizontal).get.operate(bigCanvas).get shouldBe a [CanvasState]
      DrawLine(correctDrawLineInputHorizontal).get.operate(bigCanvas).get shouldBe
        (1 to 8).map(Point(1,_)).foldLeft(bigCanvas)((canvas,p) => canvas.update(p, 'x'))
    }

    it ("should create a new CanvasState with a drawn vertical line when operated with correct input parameters in a canvas of the correct size"){
      DrawLine(correctDrawLineInputVertical).get.operate(bigCanvas).isSuccess shouldBe true
      DrawLine(correctDrawLineInputVertical).get.operate(bigCanvas).get shouldBe a [CanvasState]
      DrawLine(correctDrawLineInputVertical).get.operate(bigCanvas).get shouldBe
        (1 to 18).map(Point(_,1)).foldLeft(bigCanvas)((canvas,p) => canvas.update(p, 'x'))
    }

    it ("should return an error if the input points are not aligned vertically or horizontally"){
      DrawLine(drawLineNonAligned).get.operate(bigCanvas).isFailure shouldBe true
    }

    it ("should return an error if the input points are outside the canvas"){
      DrawLine(correctDrawLineInputVertical).get.operate(baseCanvas).isFailure shouldBe true
    }

  }

}
