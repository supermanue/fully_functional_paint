package snd.unit.controller

import snd.BaseTest
import snd.controller.InputAnalyzer
import snd.exceptions.WrongNumberOfParamsException
import snd.fixtures.InputFixtures

import scala.util.{Failure, Success}

class InputAnalyzerSpec extends BaseTest with InputFixtures {
  val analyzer = InputAnalyzer()

  describe ("Input analyzer") {
    it ("should create a command string from a correct input string"){
      analyzer.splitToInput(correctCreateCanvasInput).isSuccess shouldBe true
      analyzer.splitToInput(correctCreateCanvasInput).get shouldBe ('c', List("15", "10"))
    }
    it ("should return an error if the input string is too long"){
      analyzer.splitToInput(tooLongCreateCanvasInput).isFailure shouldBe true
    }

    it ("should return an error if the input string is too short"){
      analyzer.splitToInput(tooShortInput).isFailure shouldBe true
    }

  }


}
