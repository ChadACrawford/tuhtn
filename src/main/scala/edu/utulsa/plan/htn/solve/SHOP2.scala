package edu.utulsa.plan.htn.solve

import edu.utulsa.plan.htn.{FVFlag, Plan, Problem}


class SHOP2 extends Solver {
}

object SHOP2 {
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