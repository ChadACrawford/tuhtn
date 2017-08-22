package edu.utulsa.plan.htn

class Operator
(
  val task: PrimitiveTask,
  val variables: Seq[Variable[_]],
  val precondition: operators.Expression[Boolean],
  val postcondition: Map[Variable[_], operators.Expression[_]],
  val cost: (Arguments) => Int
) extends Functor {
  case class Result(update: Map[Variable[_], _], cost: Int)

  protected def evaluate(args: Arguments): Map[Variable[_], _] =
    postcondition.map { case (variable, expression) => variable -> expression(args) }

  def update(args: Arguments): Option[Result] = {
    if(precondition(args))
      Some(Result(evaluate(args), cost(args)))
    else
      None
  }
}
