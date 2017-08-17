package edu.utulsa.plan.htn

import scala.collection.mutable

abstract class Problem(
  val predicates: Seq[Predicate],
  val tasks: Seq[Task],
  val methods: Seq[Method],
  val operators: Seq[Operator],
  val condition: Condition
) {

  def initialState(): State = ???
  def stateIsValid(state: State): Boolean = ???
}
