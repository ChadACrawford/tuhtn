package edu.utulsa.masters.tuhtn

/**
  * State object.
  */
class State(val state: Map[Predicate[_], Any]) {
  def set[T](q: Predicate[T], value: T): State = {
    require(!state.contains(q), "Variable is not a member of initial state.")
    new State(state + (q -> value))
  }

  def get[T](q: Predicate[T]): T = {
    require(!state.contains(q), "Variable is not a member of initial state.")
    state.get(q).asInstanceOf[T]
  }

  def size: Int = state.size
}
