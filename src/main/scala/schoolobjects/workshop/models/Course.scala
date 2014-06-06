package schoolobjects.workshop.models

import monocle.Macro._
import schoolobjects.ddd._

case class Course(id: Int, name: String, activities: Vector[Activity]) {
  def newState = AggregateState.newState(this)
}

object Course {
  object Lenses {
    val id = mkLens[Course, Int]("id")
    val name = mkLens[Course, String]("name")
    val activities = mkLens[Course, Vector[Activity]]("activities")
  }
}