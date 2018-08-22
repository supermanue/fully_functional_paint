package snd.fixtures

import snd.model.Point

trait InputFixtures {

  val correctCreateCanvasInput = "C 15 10"
  val tooLongCreateCanvasInput = "C 1 2 3 4 5 6 7 8 9"
  val correctDrawLineInput = "L 2 2 2 9"
  val correctQuitCommand = "Q"

  val tooShortInput = ""
  val canvasInputNoOp = "1 2 3 4"
  val inputNonExistingOp = "H 1 2 3 4"

  val correctDrawLineInputHorizontal = Seq("2","2","2","9")
  val correctHorizontalPoint1 = Point (1,1)
  val correctHorizontalPoint2 = Point (1,8)
  val correctDrawLineInputVertical = Seq("2","2","19","2")
  val drawLineNonAligned = Seq("1","2","3","4")
  val drawLineWrongChar = Seq("L","1","2","3")
  val drawLineNonIntegerPoints = Seq("1.1","2","3","4")


  val correctCreateCanvasInputParams = Seq("15","10")
  val incorrectCreateCanvasInputParams = Seq("15.3","10")
  val incorrectCreateCanvasInputParams2 = Seq("15","X")
  val incorrectCreateCanvasInputParams3 = Seq("15","10","3")
  val incorrectCreateCanvasInputParams4 = Seq("-15","10")


  val correctDrawRectangleInput = Seq("1", "2", "5", "5")
  val correctDrawRectanglePoint1 = Point (0,1)
  val correctDrawRectanglePoint2 = Point (4,4)
  val correctDrawRectangleInputHorizontal = Seq("2", "2", "2", "9")
  val correctDrawRectangleInputBig = Seq("1", "2", "55", "5")
  val drawRectangleWrongChar = Seq("L","1","2","3")
  val drawRectangleNonIntegerPoints = Seq("1.1","2","3","4")


  val correctBucketFillInput = Seq("1", "2", "c")
  val correctBucketFillInputOtherColor = Seq("1", "2", "x")
  val bucketPoint = Point(0,1)
  val bucketPoint2 = Point(0,9)
  val bucketChar = 'c'
  val incorrectBucketFillInputParams = Seq("15.3","10", "string")
  val incorrectBucketFillInputParams2 = Seq("15","X", "c")
  val incorrectBucketFillInputParams3 = Seq("15","10","3", "c")
  val incorrectBucketFillInputParams4 = Seq("-15","10", "c")
}
