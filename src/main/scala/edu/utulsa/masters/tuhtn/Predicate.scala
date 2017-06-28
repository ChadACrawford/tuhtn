package edu.utulsa.masters.tuhtn

import scala.collection.mutable
import scala.reflect.ClassTag

class Predicate[Type: ClassTag]()
object Predicate {
  def apply[T: ClassTag](initialValue: T)(implicit stateTemplate: StateTemplate): Predicate[T] = {
    val q = new Predicate[T]()
    stateTemplate.register(q, initialValue)
    q
  }
}
