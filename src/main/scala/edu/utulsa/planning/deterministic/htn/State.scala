package edu.utulsa.planning.deterministic.htn

class State(val problem: Problem) {
  def update(assignments: Map[Predicate[_], _]): State = ???
  def get[T](predicate: Predicate[T]): T = ???
}

object State {
  def initialize(problem: Problem)
}
