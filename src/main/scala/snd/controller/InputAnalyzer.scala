package snd.controller

import scala.util.{Failure, Try}
import snd.configuration.Constants._
import snd.exceptions.WrongNumberOfParamsException

case class InputAnalyzer() {

    def splitToInput (s: String): Try[(Char, List[String])] ={
      val split = s.split("\\s+")
      if (split.length > longestCommandLength)
        return Failure(WrongNumberOfParamsException("Input had too many arguments"))

      Try (split.head.toLowerCase.charAt(0), split.tail.take(longestCommandLength).toList)
    }
}