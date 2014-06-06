object Main extends App {

  import scalaz.\/, scalaz.\/-, scalaz.-\/

  import schoolobjects._, ddd._, DDD._
  import workshop.models._, workshop.courses._, CoursesAggregate._

  /* Setup */
  val coursesRepo = new CoursesRepository
  val course = coursesRepo.getCourse(1)
  val newActivity = Activity(400, "Learn about everything else", Vector[Event]())

  1 to 6 foreach { _ => println() }

  println("Starting course: " + course)
  println()
  println("*******************************************")
  println()


  /* Create activity */

  println("Create activity: ")
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
  println("*******************************************")
  println()

  /* Remove activity */

  println("Delete activity: ")
  println()

  val targetActivity = course.activities(0)

  val deleteActivity = for {
    a <- removeActivity(targetActivity)
  } yield a

  val deleteActivityResult = deleteActivity(course.newState)

  deleteActivityResult match {
    case \/-(result) => coursesRepo.updateCourse(result)
    case -\/(errors) => println("Errors: " + errors)
  }

  println()
  println("*******************************************")
  println()

  /* Failing update */

  println("Bad update: ")
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

  println()
  println("*******************************************")
  println()

  1 to 3 foreach { _ => println() }

}