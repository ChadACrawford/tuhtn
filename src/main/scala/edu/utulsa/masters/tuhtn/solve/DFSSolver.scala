package edu.utulsa.masters.tuhtn.solve
import edu.utulsa.masters.tuhtn.{Arguments, Problem}


class DFSSolver extends Solver {
  import SolverTypes._
  override def solve(problem: Problem)(goal: TaskCall): Seq[TaskCall] = ???

  protected def search(problem: Problem)(call: TaskCall): Seq[TaskCall] = ???
}
