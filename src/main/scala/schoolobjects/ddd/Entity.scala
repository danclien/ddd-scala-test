package schoolobjects.ddd

trait Entity[A] {
  def id(entity: A): Int
}