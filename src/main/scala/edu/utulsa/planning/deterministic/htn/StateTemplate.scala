package edu.utulsa.planning.deterministic.htn

import scala.collection.mutable

/**
  * Created by chad on 6/26/17.
  */
class StateTemplate {
  val state = mutable.Map[Predicate[_], _]()
  def set[T](q: Predicate[T], initialValue: T): Unit = {
    state.put(q, initialValue)
  }
}
