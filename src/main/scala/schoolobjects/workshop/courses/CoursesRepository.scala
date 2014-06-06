package schoolobjects.workshop.courses

import scalaz.{ \/, -\/, \/- }

import schoolobjects._, ddd._, DDD._
import workshop.models._, workshop.courses._, CoursesAggregate._

class CoursesRepository {
  def getCourse(id: Int) = courses(id)

  def updateCourse[A](result: CourseUpdateMResult[A]): Unit = result match {
    case \/-((AggregateState(course, actions), retValue)) => {
      println(s"Saving repository:")
      println(s"Course: $course")
      println(s"Actions: $actions")
    }
    case -\/(errors) => {
      println(s"Unable to save: $errors")
    }
  }

  val courses = Vector(
    Course(0, "Empty Course", Vector[Activity]()),
    Course(1, "Comic Books", Vector(
        Activity(11, "Learn about X-Men", Vector[Event](
            Event(111, "Session 1.1")
          )
        )
      )
    ),
    Course(2, "Comic Books", Vector(
        Activity(21, "Learn about X-Men", Vector[Event](
            Event(211, "Session 1.1"),
            Event(212, "Session 1.2"),
            Event(213, "Session 1.3")
          )
        ),
        Activity(22, "Learn about The Avengers", Vector[Event](
            Event(221, "Session 2.1")
          )
        ),
        Activity(23, "Learn about Spider-Man", Vector[Event](
            Event(231, "Session 3.1"),
            Event(232, "Session 3.2")
          )
        )
      )
    ) 
  )  
}