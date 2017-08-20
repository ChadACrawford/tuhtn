package edu.utulsa.plan.htn

import com.sun.javaws.exceptions.InvalidArgumentException

/**
  * Wrapper for arguments passed to a method or operator.
  */
class Arguments private(val values: Map[Variable[_], _] = Map()) {
  def get[T](variable: Variable[T]): T = {
    require(values contains variable, "Attempted to access a variable with no associated value in arguments context.")
    values(variable).asInstanceOf[T]
  }
  def apply[T](variable: Variable[T]) = get(variable)
}

object Arguments {
  def apply(state: State, predicates: Seq[Predicate[_]] = Seq(), constants: Map[Variable[_], _] = Map()): Arguments = {
    val values = {
      predicates.map {
        case predicate: Predicate[_] => predicate -> state.get(predicate)
      } ++ constants.map {
        case (variable: Variable[_], value: _) => variable -> value
      }
    }.toMap
    new Arguments(values)
  }
}