package schoolobjects.workshop.data

import scala.slick.driver.H2Driver.simple._

class Courses(tag: Tag) extends Table[(Int, String)](tag, "courses") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")

  def * = (id, name)
}

