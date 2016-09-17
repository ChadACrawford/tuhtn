package edu.utulsa.masters.tuhtn

import scala.collection.mutable

//object State {
//  def $[T](variable: Variable[T])(implicit state: State): T = state.get(variable)
//}

/**
  * State object.
  */
class State(val variables: mutable.HashMap[Variable[_], Any]) {

  def set[T](variable: Variable[T], value: T): State = {
    variables.put(variable, value)
    this
  }

  def get[T](variable: Variable[T]): T = {
    if(variables.contains(variable))
      variables.get(variable).asInstanceOf[T]
    else
      throw new NullPointerException(s"Variable has no value.")
  }

  def size: Int = variables.size
}

object State {
  def apply(): State = new State(mutable.HashMap())
  def apply(variables: mutable.HashMap[Variable[_], Any]): State = new State(variables)
}

class Result

case class Failed() extends Result

/**
  * Update state.
  *
  * @param parent
  */
case class Update(parent: State) extends Result {

  val tmpVars = mutable.HashMap.empty[Variable[_], Any]

  def get[T](variable: Variable[T]): T = {
    if(tmpVars.contains(variable)) {
      tmpVars(variable).asInstanceOf[T]
    }
    else {
      variable match {
        case v: Variable[T] => parent.get(v)
      }
    }
  }

  def set[T](variable: Variable[T], value: T): Update = {
    tmpVars(variable) = value
    this
  }

  def size: Int = parent.size + tmpVars.size

  def update: State = {
    val nmap = parent.variables.clone
    tmpVars.foreach { case (key, value) => nmap.put(key, value) }
    new State(nmap)
  }

}
