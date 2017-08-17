package edu.utulsa.plan.htn

class Operator
(
  val task: PrimitiveTask,
  val variables: Seq[Variable[_]],
  val precondition: Condition,
  val postcondition: Map[Predicate[_], Operation[_]]
) {
  abstract class Result
  case class Success(cost: Int) extends Result
  case class Failure() extends Result

  def check(args: Arguments): Result = ???
}
