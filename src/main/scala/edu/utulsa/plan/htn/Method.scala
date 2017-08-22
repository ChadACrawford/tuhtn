package edu.utulsa.plan.htn

class Method
(
  val task: CompoundTask,
  val variables: Seq[Variable[_]],
  val free: Seq[FreeVariable[_]],
  val precondition: operators.Expression[Boolean],
  val subtasks: TaskOrdering
) extends Functor {
}
