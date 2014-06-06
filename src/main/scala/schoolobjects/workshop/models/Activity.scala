package schoolobjects.workshop.models

import monocle.Macro._

case class Activity(id: Int, name: String, events: Vector[Event])

object Activity {
  object Lenses {
    val id = mkLens[Activity, Int]("id")
    val name = mkLens[Activity, String]("name")
    val events = mkLens[Activity, Vector[Event]]("events")
  }
}