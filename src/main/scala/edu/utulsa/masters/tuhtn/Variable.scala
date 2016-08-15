package edu.utulsa.masters.tuhtn

import scala.collection.mutable

class Variable[Type, Context <: Environment] {
  import Variable._

  def get(implicit state: Context): Type = state.get(this)
  def ~(that: Type)(implicit state: UState): State = state.set(this, that)
  def ~+(that: Type)(implicit state: UState, op: AddOp[Type]): State = state.set(this, op.add(get, that))
  def ~-(that: Type)(implicit state: UState, op: SubOp[Type]): State = state.set(this, op.sub(get, that))
  def ~/(that: Type)(implicit state: UState, op: DivOp[Type]): State = state.set(this, op.div(get, that))
  def ~*(that: Type)(implicit state: UState, op: MulOp[Type]): State = state.set(this, op.mul(get, that))

  val constraints = mutable.ListBuffer[ConstraintFunc[Type]]()

  def addConstraint(constraintFunc: ConstraintFunc[Type]): this.type = {
    constraints.append(constraintFunc)
    this
  }

  def validate(implicit state: State): Boolean = constraints.map(c => c(state.get(this))).reduce(_ & _)
}

class NamedVariable[T](val name: String) extends Variable[T]

object Variable {
  type ConstraintFunc[T] = (T) => Boolean

  def apply[T](): Variable[T] = new Variable[T]()
  def apply[T](name: String): Variable[T] = new NamedVariable[T](name)
}

object Argument {
  def apply[T]() = new Variable[T]()
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
