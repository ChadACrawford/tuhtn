package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class Task(val name: Symbol)(implicit tasks: mutable.ListBuffer[Task]) {
  tasks.append(this)
}

case class PrimitiveTask(override val name: Symbol)(implicit tasks: mutable.ListBuffer[Task]) extends Task(name)

case class CompoundTask(override val name: Symbol)(implicit tasks: mutable.ListBuffer[Task]) extends Task(name)
