package schoolobjects.workshop.models

import monocle.Macro._

case class Event(id: Int, name: String)

object Event {
  object Lenses {
    val id = mkLens[Event, Int]("id")
    val name = mkLens[Event, String]("name")  
  }
}