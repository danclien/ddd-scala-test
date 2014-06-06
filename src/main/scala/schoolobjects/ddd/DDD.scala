package schoolobjects.ddd

import scalaz._, Scalaz._

object DDD {
  type AggregateActions = Vector[EntityAction[Any]]

  type AggregateUpdateResult[+A] = \/[Vector[String], A]
  type AggregateUpdateM[R, A] = StateT[AggregateUpdateResult, AggregateState[R], A]
  type AggregateUpdateMResultTuple[A, B] = (AggregateState[A], B)
  type AggregateUpdateMResult[A, B] = AggregateUpdateResult[AggregateUpdateMResultTuple[A, B]]

  def printResult[A, B](updateResults: AggregateUpdateMResult[A, B]) = {
    updateResults match {
      case \/-(right) => {
        println("Result: " + right._2)
        println("Aggregate: " + right._1.aggregate)
        println("Changes: " + right._1.actions)
      } 
      case -\/(left) => println("Errors: " + left)
    }      
  }

  def left(error: String) = -\/(Vector(error))
  def left(errors: Vector[String]) = -\/(errors)

  def right[A, B](aggregate: A, actions: AggregateActions, returnValue: B) = {
    \/-((
      AggregateState(aggregate, actions),
      returnValue
    ))
  }  
}