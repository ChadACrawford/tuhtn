package edu.utulsa.plan.htn

import com.sun.javaws.exceptions.InvalidArgumentException

/**
  * Wrapper for arguments passed to a method or operator.
  */
class Arguments private
(
  val state: State,
  val predicates: Map[Variable[_], Predicate[_]] = Map(),
  val constants: Map[Variable[_], _] = Map()
) {
  def get[T](variable: Variable[T]): T = {
    if(predicates contains variable) {
      state.get(predicates(variable)).asInstanceOf[T]
    }
    else {
      constants(variable).asInstanceOf[T]
    }
  }
  def apply[T](variable: Variable[T]) = get(variable)
}
