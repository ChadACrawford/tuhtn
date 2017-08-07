package edu.utulsa.planning.deterministic.htn

class State {
  def update(assignments: Map[Predicate[_], _]): State = ???
  def get[T](predicate: Predicate[T]): T = ???
}
