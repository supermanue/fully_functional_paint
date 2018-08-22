package snd.unit.controller

import snd.BaseTest
import snd.command.CreateCanvas
import snd.controller.CommandProcessor
import snd.fixtures.InputFixtures

class CommandProcessorSpec extends BaseTest with InputFixtures {
  val commandProcesor = CommandProcessor()

  describe ("Command Processor") {
    it ("should return a command when the input is correct"){
      commandProcesor.process(('c', Seq[String]("1", "2"))).isSuccess shouldBe true
      commandProcesor.process(('c', Seq[String]("1", "2"))).get shouldBe a [CreateCanvas]
    }

    it ("should return an error when the input command does not exist"){
      commandProcesor.process(('X', Seq[String]("1", "2"))).isFailure shouldBe true
    }

    it ("should return an error when the input is not correct for a given command"){
      commandProcesor.process(('c', Seq[String]("1", "2", "3"))).isFailure shouldBe true
    }

  }


}
