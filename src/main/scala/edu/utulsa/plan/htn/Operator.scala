package edu.utulsa.plan.htn

class Operator
(
  val task: PrimitiveTask,
  val variables: Seq[Variable[_]],
  val precondition: Condition,
  val postcondition: Map[Variable[_], Operation[_]]
) {
  case class Result(newState: State, cost: Int)

  def update(state: State, args: Arguments): Option[Result] = ???
}
