package edu.utulsa.masters.tuhtn

import scala.collection.mutable
import scala.reflect.ClassTag

class Variable[Type: ClassTag] {
  import Variable._

  def get(implicit state: State): Type = state.get(this)

  def ~(that: Type)(implicit state: State, update: Update): Update = update.set(this, that)
  def ~+(that: Type)(implicit state: State, update: Update, op: AddOp[Type]): Update = update.set(this, op.add(get, that))
  def ~-(that: Type)(implicit state: State, update: Update, op: SubOp[Type]): Update = update.set(this, op.sub(get, that))
  def ~/(that: Type)(implicit state: State, update: Update, op: DivOp[Type]): Update = update.set(this, op.div(get, that))
  def ~*(that: Type)(implicit state: State, update: Update, op: MulOp[Type]): Update = update.set(this, op.mul(get, that))

  val constraints = mutable.ListBuffer[ConstraintFunc[Type]]()

  def addConstraint(constraintFunc: ConstraintFunc[Type]): this.type = {
    constraints.append(constraintFunc)
    this
  }

  def validate(implicit state: State): Boolean = constraints.map(c => c(get)).reduce(_ & _)

  def getType(implicit ct: ClassTag[Type]): Class[Type] = ct.runtimeClass.asInstanceOf[Class[Type]]
}

class NamedVariable[T: ClassTag](val name: String) extends Variable[T]

object Variable {
  type ConstraintFunc[T] = (T) => Boolean

  def apply[T: ClassTag](value: T)(implicit state: State, update: Update): Variable[T] = {
    val v = new Variable[T]()
    v ~ value
    v
  }
  def apply[T: ClassTag](name: String)(value: T)(implicit state: State, update: Update): Variable[T] = {
    val v = new NamedVariable[T](name)
    v ~ value
    v
  }
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
