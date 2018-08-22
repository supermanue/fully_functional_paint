package snd.controller

import snd.exceptions.WrongInputFormatException
import snd.command._

import scala.util.{Failure, Try}

case class CommandProcessor()
{
   def process (input: (Char, Seq[String])): Try[Command] = input match {
       
      case ('c', params)  => CreateCanvas(params)
      case ('l', params)  => DrawLine(params)
      case ('r', params)  => DrawRectangle(params)
      case ('b', params)  => BucketFill(params)
      case ('q', params)  => Quit(params)

      case (other, _) => Failure(WrongInputFormatException(s"$other is not a known command"))
      case _ => Failure(WrongInputFormatException("Wrong input format"))

    }





}