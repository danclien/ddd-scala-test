package schoolobjects.workshop.courses

import monocle.syntax._
import monocle.function.Index._

import schoolobjects._, ddd._, DDD._
import workshop.models._

object CoursesAggregate {
  /* Setup */

  type CourseUpdateM[A] = AggregateUpdateM[Course, A]
  type CourseUpdateMResultTuple[A] = AggregateUpdateMResultTuple[Course, A]

  object CourseUpdateM {
    def apply[A](f: AggregateState[Course] => AggregateUpdateResult[(AggregateState[Course], A)]): CourseUpdateM[A] = AggregateUpdateM[Course, A](f)
  }


  /* Commands */

  def addActivity(activity: Activity) = CourseUpdateM[Unit] { state =>
    import Course.Lenses.{ activities }

    val AggregateState(course, actions) = state

    // Add an activity to a course
    val newCourse: Course = (course |-> activities) modify (_ :+ activity)

    // Add the action to the actions list
    val newChanges: Vector[EntityAction[Any]] = actions :+ Create(activity)

    right(newCourse, newChanges, Unit)        // Helper method in schoolobjects.ddd.DDD._
                                              // Equivalent to v-------

    // \/-((
    //   AggregateState(newCourse, newChanges),  // <-- new updated state
    //   Unit                                    // <-- return value of the function
    // ))
  }

  def removeActivity(activity: Activity) = CourseUpdateM[Unit] { state =>
    import Course.Lenses.{ activities }

    val AggregateState(course, actions) = state

    val newCourse = (course |-> activities) modify (_.filter(_ != activity))
    val newChanges = actions :+ Delete(activity)

    right(newCourse, newChanges, Unit)
  }

  def fail = CourseUpdateM[Int] { state =>
    left("CoursesAggregate.fail always fails") // Helper method in schoolobjects.ddd.DDD._
                          // Equivalent to:
                          // -\/(Vector("Does not work"))
  }  
}