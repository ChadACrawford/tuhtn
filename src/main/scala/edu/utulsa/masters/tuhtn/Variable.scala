package edu.utulsa.masters.tuhtn

import scala.collection.mutable

class Variable[T](val name: String) {
  import Variable._

  def get(implicit state: UState): T = state.get(this)
  def ~(that: T)(implicit state: UState): State = state.set(this, that)
  def +~(that: T)(implicit state: UState, op: AddOp[T]): State = state.set(this, op.add(get, that))
  def -~(that: T)(implicit state: UState, op: SubOp[T]): State = state.set(this, op.sub(get, that))
  def /~(that: T)(implicit state: UState, op: DivOp[T]): State = state.set(this, op.div(get, that))
  def *~(that: T)(implicit state: UState, op: MulOp[T]): State = state.set(this, op.mul(get, that))

  val constraints = mutable.ListBuffer[ConstraintFunc[T]]()

  def addConstraint(constraintFunc: ConstraintFunc[T]): this.type = {
    constraints.append(constraintFunc)
    this
  }

  def validate(implicit state: State): Boolean = constraints.map(c => c(state.get(this))).reduce(_ & _)
}

object Variable {
  type ConstraintFunc[T] = (T) => Boolean

  def apply[T](name: String): Variable[T] = new Variable[T](name)
}

class AddOp[T](val add: (T, T) => T)
object AddOp {
  implicit object Int extends AddOp[Int]((a,b) => a+b)
  implicit object Double extends AddOp[Double]((a,b) => a+b)
  implicit object Float extends AddOp[Float]((a,b) => a+b)
}

class SubOp[T](val sub: (T, T) => T)
object SubOp {
  implicit object Int extends SubOp[Int]((a,b) => a-b)
  implicit object Double extends SubOp[Double]((a,b) => a-b)
  implicit object Float extends SubOp[Float]((a,b) => a-b)
}

class DivOp[T](val div: (T, T) => T)
object DivOp {
  implicit object Int extends DivOp[Int]((a,b) => a/b)
  implicit object Double extends DivOp[Double]((a,b) => a/b)
  implicit object Float extends DivOp[Float]((a,b) => a/b)
}

class MulOp[T](val mul: (T, T) => T)
object MulOp {
  implicit object Int extends MulOp[Int]((a,b) => a*b)
  implicit object Double extends MulOp[Double]((a,b) => a*b)
  implicit object Float extends MulOp[Double]((a,b) => a*b)
}
