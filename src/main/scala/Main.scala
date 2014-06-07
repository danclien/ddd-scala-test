object Main extends App {

  import scalaz.\/, scalaz.\/-, scalaz.-\/

  import schoolobjects._, ddd._, DDD._
  import workshop.models._, workshop.courses._, CoursesAggregate._

  /* Setup */
  val coursesRepo = new CoursesRepository
  val course = coursesRepo.getCourse(1)
  val newActivity = Activity(400, "Learn about DC comics", Vector[Event]())

  1 to 6 foreach { _ => println() }

  /* Create activity */

  println("Create activity")
  println("=================")
  println()
  println(s"Before: $course")
  println(s"New activity: $newActivity")
  println()

  // Commands to be performed
  val createActivity = for {
    a <- addActivity(newActivity)
  } yield a

  // Performing the commands
  val createActivityResult = createActivity(course.newState)

  // Processing the result of the commands (e.g. persisting to the database/returning errors)
  createActivityResult match {
    case \/-(result) => coursesRepo.updateCourse(result)
    case -\/(errors) => println("Errors: " + errors)
  }

  println()
  println()

  /* Remove activity */

  val targetActivity = course.activities(0)

  println("Delete activity")
  println("=================")
  println()
  println(s"Before: $course")
  println(s"Deleting activity: $targetActivity")
  println()

  val deleteActivity = for {
    a <- removeActivity(targetActivity)
  } yield a

  val deleteActivityResult = deleteActivity(course.newState)

  deleteActivityResult match {
    case \/-(result) => coursesRepo.updateCourse(result)
    case -\/(errors) => println("Errors: " + errors)
  }

  println()
  println()

  /* Failing update */

  println("Bad update ")
  println("============")
  println()

  val badUpdate = for {
    a <- addActivity(newActivity)
    _ <- fail
  } yield a

  val badResult = badUpdate(course.newState)

  badResult match {
    case \/-(result) => coursesRepo.updateCourse(result)
    case -\/(errors) => println("Errors: " + errors)
  }

  1 to 5 foreach { _ => println() }

}