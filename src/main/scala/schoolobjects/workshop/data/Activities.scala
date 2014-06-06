package schoolobjects.workshop.data

import scala.slick.driver.H2Driver.simple._

class Activities(tag: Tag) extends Table[(Int, String, Int)](tag, "activities") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  def courseId = column[Int]("courseId")

  def * = (id, name, courseId)
}

