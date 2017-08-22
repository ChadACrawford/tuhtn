package edu.utulsa.plan.htn.solve

import edu.utulsa.plan.htn.{Plan, Problem}

abstract class Solver {
  def solve(problem: Problem): Plan
}
