package schoolobjects.ddd

import DDD._

case class AggregateState[A](aggregate: A, actions: Vector[EntityAction[Any]])

object AggregateState {
  def newState[A](aggregate: A) = AggregateState(aggregate, Vector[EntityAction[Any]]())
}