package edu.utulsa.planning.deterministic.htn

abstract class Problem {
  val predicates: Map[Symbol, Predicate]
  val tasks: Map[Symbol, Task]
  val methods: Map[CompoundTask, Seq[Method]]
  val operators: Map[PrimitiveTask, Seq[Operator]]
}
