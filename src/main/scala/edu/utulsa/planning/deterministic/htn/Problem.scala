package edu.utulsa.planning.deterministic.htn

import scala.collection.mutable

abstract class Problem(
  val predicates: Map[Symbol, Predicate],
  val tasks: Map[Symbol, Task],
  val methods: Map[CompoundTask, Seq[Method]],
  val operators: Map[PrimitiveTask, Seq[Operator]],
  val constraints: Seq[Condition]
) {
  def initialState(): State = {
  }
}
