package schoolobjects.ddd

sealed trait EntityAction[+A]

case class Create[+A](value: A) extends EntityAction[A]
case class Update[+A](value: A) extends EntityAction[A]
case class Delete[+A](value: A) extends EntityAction[A]
case class Undelete[+A](value: A) extends EntityAction[A]