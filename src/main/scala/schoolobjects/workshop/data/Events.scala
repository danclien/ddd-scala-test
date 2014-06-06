package schoolobjects.workshop.data

import scala.slick.driver.H2Driver.simple._

class Events(tag: Tag) extends Table[(Int, String, Int)](tag, "events") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  def activityId = column[Int]("activityId")

  def * = (id, name, activityId)
}

