package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class Environment[Context <: Environment[Context]] {
  def set[T](variable: Variable[T, Context], value: T): Context
  def get[T](variable: Variable[T, Context]): T
}

abstract class State extends Environment[State] {
  override def set[T](variable: Variable[T, State], value: T): State
  override def get[T](variable: Variable[T, State]): T
}

object State {
  def $[T](variable: Variable[T, State])(implicit state: State): T = state.get(variable)
}

/**
  * Constant state.
  */
class CState(val variables: Map[Variable[_, State], Any]) extends State {

  override def set[T](variable: Variable[T, State], value: T): State = {
    variable match {
      case svar: Variable[T, State] => new CState (variables ++ Seq(svar -> value))
    }
  }

  override def get[T](variable: Variable[T, State]): T = {
    if(variables.contains(variable))
      return variables.get(variable).asInstanceOf[T]
    else
      throw new NullPointerException(s"Variable has no value.")
  }

}

/**
  * Update state.
  *
  * @param parent
  */
class UState(val parent: CState) extends State {

  val tmpVars: mutable.Map[Variable[_, State], Any] = mutable.Map()

  override def get[T](variable: Variable[T, State]): T = {
    if(tmpVars.contains(variable)) {
      tmpVars(variable).asInstanceOf[T]
    }
    else {
      parent.get(variable)
    }
  }

  override def set[T](variable: Variable[T, State], value: T): State = {
    tmpVars(variable) = value
    this
  }

  def stateMap: Map[Variable[_, State], Any] = {
    tmpVars
      .filter{ case (v: Variable[_, State], a: Any) => parent.variables.contains(v) }
      .toMap
  }

  def update: CState = {
    new CState(parent.variables ++ stateMap)
  }

}

class Arguments extends Environment[Arguments] {

  val args = mutable.Map[Variable[_, Arguments], Any]()

  override def set[T](variable: Variable[T, Arguments], value: T): Arguments = {
    args(variable) = value
    this
  }

  override def get[T](variable: Variable[T, Arguments]): T = {
    args(variable).asInstanceOf[T]
  }

}
