package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class State {

  def set[T](variable: Variable[T], value: T): State
  def get[T](variable: Variable[T]): T

}

object State {

  def $[T](variable: Variable[T])(implicit state: State): T = state.get(variable)

}

/**
  * Constant state.
  */
class CState(val variables: Map[Variable[_], Any]) extends State {

  override def set[T](variable: Variable[T], value: T): State = {
    variable match {
      case svar: Variable[T] => new CState (Map (variables.toSeq: _*) += (svar -> value) )
    }
  }

  override def get[T](variable: Variable[T]): T = {
    if(variables.contains(variable))
      return variables.get(variable).asInstanceOf[T]
    else
      throw new NullPointerException(s"Variable '${variable.name}' has no value.")
  }

}

/**
  * Update state.
  *
  * @param parent
  */
class UState(val parent: CState) extends State {

  val tmpVars: mutable.Map[Variable[_], Any] = mutable.Map()

  override def get[T](variable: Variable[T]): T = {
    if(tmpVars.contains(variable)) {
      tmpVars(variable).asInstanceOf[T]
    }
    else {
      parent.get(variable)
    }
  }

  override def set[T](variable: Variable[T], value: T): State = {
    tmpVars(variable) = value
    this
  }

  def stateMap: Map[Variable[_], Any] = {
    tmpVars
      .filter{ case (v: Variable[_], a: Any) => parent.variables.contains(v) }
      .toMap
  }

  def update: CState = {
    new CState(parent.variables ++ stateMap)
  }

}