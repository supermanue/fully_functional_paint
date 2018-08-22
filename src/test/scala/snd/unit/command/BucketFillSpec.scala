package snd.unit.command

import snd.BaseTest
import snd.command.{BucketFill, DrawRectangle}
import snd.controller.GraphicalLibrary
import snd.fixtures.{CanvasFixtures, InputFixtures}
import snd.model.{CanvasState, Point}

class BucketFillSpec extends BaseTest with InputFixtures with CanvasFixtures {

  describe ("BucketFill") {
    it ("should create new DrawRectangle operation"){
      BucketFill(pointInBaseCanvas, bucketChar ) shouldBe a [BucketFill]
      BucketFill(correctBucketFillInput).isSuccess shouldBe true
      BucketFill(correctBucketFillInput).get shouldBe a [BucketFill]
    }

    it ("should return an error if the input format is incorrect"){
      BucketFill(incorrectBucketFillInputParams).isFailure shouldBe true
      BucketFill(incorrectBucketFillInputParams2).isFailure shouldBe true
      BucketFill(incorrectBucketFillInputParams3).isFailure shouldBe true
    }

    it ("should return an error if any of the input points are outside the canvas"){
      BucketFill(incorrectBucketFillInputParams4).get.operate(baseCanvas).isFailure shouldBe true
    }

    it ("should modify all the points in an empty canvas"){
      BucketFill(correctBucketFillInput).get.operate(baseCanvas).isSuccess shouldBe true
      BucketFill(correctBucketFillInput).get.operate(baseCanvas).get shouldBe
        (for {
          x <- 0 until baseCanvas.xSize
          y <- 0 until baseCanvas.ySize
         } yield (x,y)).
          foldLeft(
            CanvasState(baseCanvas.xSize, baseCanvas.ySize))(
            (c,p) => c.update(Point (p._1, p._2), bucketChar))
    }

    it ("should only paint points with the same color as the initial point"){
      val canvasWithAPoint = baseCanvas.update(bucketPoint, bucketChar)
      val canvasWithALine = GraphicalLibrary.createHorizontalLine(bucketPoint, bucketPoint2, baseCanvas.copy())
      val canvasWithASquare = DrawRectangle(bucketPoint, correctDrawRectanglePoint2).operate(baseCanvas).get

      BucketFill(correctBucketFillInput).get.operate(canvasWithAPoint).isSuccess shouldBe true
      BucketFill(correctBucketFillInput).get.operate(canvasWithAPoint).get shouldBe canvasWithAPoint


      BucketFill(correctBucketFillInputOtherColor).get.operate(canvasWithALine).isSuccess shouldBe true
      BucketFill(correctBucketFillInputOtherColor).get.operate(
        BucketFill(correctBucketFillInput).get.operate(canvasWithALine).get).get shouldBe canvasWithALine

      BucketFill(correctBucketFillInputOtherColor).get.operate(canvasWithASquare).isSuccess shouldBe true
      BucketFill(correctBucketFillInputOtherColor).get.operate(
        BucketFill(correctBucketFillInput).get.operate(canvasWithASquare).get).get shouldBe canvasWithASquare


    }


  }



}
