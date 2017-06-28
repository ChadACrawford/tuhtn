package edu.utulsa.masters.tuhtn

import scala.collection.mutable

/**
  * Created by chad on 6/26/17.
  */
class StateTemplate {
  val state = mutable.Map[Predicate[_], _]()
  def register[T](q: Predicate[T], initialValue: T): Unit = {
    state.put(q, initialValue)
  }
}
