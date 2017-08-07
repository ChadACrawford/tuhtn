package edu.utulsa.planning.deterministic.htn

import scala.collection.mutable

abstract class Task(val name: Symbol) {
}

case class PrimitiveTask(override val name: Symbol) extends Task(name)

case class CompoundTask(override val name: Symbol) extends Task(name)
