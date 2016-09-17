package edu.utulsa.masters.tuhtn

import scala.collection.mutable
import scala.reflect.ClassTag

class Arguments {
  val indices = mutable.ListBuffer[Argument[_]]()

  def add[T](variable: Argument[T]): this.type = {
    indices append variable
    this
  }

  def get[T](index: Int)(implicit args: Seq[Any]): T = {
    indices(index).getType match {
      case c: Class[T] => args(index).asInstanceOf[T]
      case _ => throw new IllegalArgumentException(s"Illegal type for argument at position $index.")
    }
  }

  def valid(args: Seq[Any]): Boolean = {
    if(args.size != indices.size) return false
    (args zip indices)
      .map { case (a, v) => a.getClass == v.getType }
      .reduce(_ && _)
  }

  def size: Int = indices.size

  def getType(index: Int): Class[_] = indices(index).getType
}

class Argument[T: ClassTag] {
  def getType(implicit ct: ClassTag[T]): Class[T] = ct.runtimeClass.asInstanceOf[Class[T]]
}

object Argument {
  def apply[T: ClassTag](): Argument[T] = new Argument[T]()
}