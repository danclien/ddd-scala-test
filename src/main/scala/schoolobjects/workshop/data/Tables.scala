package schoolobjects.workshop.data

import scala.slick.driver.H2Driver.simple._

object Tables {
  val Courses = TableQuery[Courses]  
  val Activities = TableQuery[Activities]
  val Events = TableQuery[Events]
}