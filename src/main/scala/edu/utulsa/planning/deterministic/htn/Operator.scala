package edu.utulsa.planning.deterministic.htn

/**
  * Created by chad on 6/26/17.
  */
class Operator(val task: PrimitiveTask, val precondition: Precondition, val postcondition: Postcondition) {
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