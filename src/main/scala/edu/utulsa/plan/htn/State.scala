package edu.utulsa.plan.htn

class State(val problem: Problem) {
  def update(assignments: Map[Predicate[_], _]): State = ???
  def get[T](predicate: Predicate[T]): T = ???
}
