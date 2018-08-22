package snd.command

import snd.model.CanvasState

import scala.util.Try

trait Command {
  def operate (c: CanvasState): Try[CanvasState]
}
