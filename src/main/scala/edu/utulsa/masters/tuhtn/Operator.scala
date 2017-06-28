package edu.utulsa.masters.tuhtn

/**
  * Created by chad on 6/26/17.
  */
class Operator(val task: PrimitiveTask) {
  var precondition: Precondition = null
  var postcondition: (State) => State = null
  def ?(precondition: Precondition): this.type = {
    this.precondition = precondition
    this
  }
  def ->(f: (State) => State): this.type = {
    this.postcondition = f
    this
  }
}

//object Operator {
//  implicit class AnyOps[T](val a: Predicate[T]) {
//    def ~=(b: T)(implicit state: State) = ???
//  }
//  implicit class BooleanOps(val a: Predicate[Boolean]) {
//    def ~!(implicit state: State) = ???
//  }
//  implicit class IntOps(val a: Predicate[Int]) {
//    def ~+(b: Int)(implicit state: State) = ???
//    def ~-(b: Int)(implicit state: State) = ???
//    def ~*(b: Int)(implicit state: State) = ???
//    def ~/(b: Int)(implicit state: State) = ???
//  }
//}