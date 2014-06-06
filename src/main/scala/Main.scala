object Main extends App {

  import scalaz.\/, scalaz.\/-, scalaz.-\/

  import schoolobjects._, ddd._, DDD._
  import workshop.models._, workshop.courses._, CoursesAggregate._

  /* Setup */
  val coursesRepo = new CoursesRepository
  val course = coursesRepo.getCourse(1)
  val newActivity = Activity(400, "Learn about everything else", Vector[Event]())

  println()
  println("Current course: " + course)
  println()
  println("*******************************************")
  println()


  /* Create activity */

  val createActivity = for {
    a <- addActivity(newActivity)
  } yield a

  val createActivityResult = createActivity(course.newState)

  println("Create activity: ")
  println()
  coursesRepo.updateCourse(createActivityResult)
  println()
  println("*******************************************")
  println()



  /* Remove activity */

  val targetActivity = course.activities(0)

  val deleteActivity = for {
    a <- removeActivity(targetActivity)
  } yield a

  val deleteActivityResult = deleteActivity(course.newState)

  println("Delete activity: ")
  println()
  coursesRepo.updateCourse(deleteActivityResult)
  println()
  println("*******************************************")
  println()



  /* Failing update */

  val badUpdate = for {
    a <- addActivity(newActivity)
    _ <- fail
  } yield a

  val badResult = badUpdate(course.newState)

  println("Bad update: ")
  println()
  coursesRepo.updateCourse(badResult)
  println()
  println("*******************************************")
  println()
}