package schoolobjects.ddd

import scalaz._, Scalaz._
import DDD._

object AggregateUpdateM {
  def apply[R, A](f: AggregateState[R] => AggregateUpdateResult[(AggregateState[R], A)]): AggregateUpdateM[R, A] = {
    StateT[AggregateUpdateResult, AggregateState[R], A](f)
  }
}