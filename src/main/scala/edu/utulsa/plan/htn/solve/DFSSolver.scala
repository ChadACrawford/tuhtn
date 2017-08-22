package edu.utulsa.plan.htn.solve

import edu.utulsa.plan.htn._


class DFSSolver extends Solver {
  override def solve(problem: Problem) = ???

  def expand(task: Task, state: State): Functor = {
    task match {
      case CompoundTask() =>
      case PrimitiveTask() =>
    }
  }
}

object DFSSolver {
  def solve(problem: Problem): Plan = ???

  object flags {
    case class INT_MIN(startAt: Int) extends FVFlag[Int]

    /**
      * Indicates that we should seek to maximize the integer.
      * @param startAt
      * @param stopAt
      * @return
      */
    case class INT_MAX(startAt: Int, stopAt: Int = 0) extends FVFlag[Int]
  }
}