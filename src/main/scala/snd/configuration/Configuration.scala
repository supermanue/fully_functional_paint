package snd.configuration

import snd.controller.{CommandProcessor, InputAnalyzer}

object Configuration {
  val inputAnalyzer = new InputAnalyzer
  val commandProcessor = new CommandProcessor

}

object Constants {
  val longestCommandLength = 5
}