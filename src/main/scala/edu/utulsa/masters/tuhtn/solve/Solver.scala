package edu.utulsa.masters.tuhtn.solve

import edu.utulsa.masters.tuhtn.{Arguments, Problem}

abstract class Solver {
  import SolverTypes._
  def solve(problem: Problem)(goal: TaskCall): Seq[TaskCall]
}

object SolverTypes {
  type TaskCall = (Symbol, Seq[Any])
}