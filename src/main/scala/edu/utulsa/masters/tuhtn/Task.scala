package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class Task(val name: Symbol)(implicit tasks: mutable.ListBuffer[Task]) {
  tasks.append(this)

  implicit val args: Arguments = new Arguments()
  private var prec: () => Unit = null
  def precondition(f: () => Unit): this.type = {
    this.prec = f
    this
  }

  def valid(callArgs: Seq[Any]): Boolean = {
    args.valid(callArgs)
  }
}

object Task {
}

abstract class PrimitiveTask(override val name: Symbol)(implicit tasks: mutable.ListBuffer[Task]) extends Task(name) {
  var post: () => Unit = null
  def postcondition(f: () => Unit): this.type = {
    this.post = f
    this
  }

  var cost = 0
  def cost(cost: Int): this.type = {
    this.cost = cost
    this
  }
}

abstract class CompoundTask(override val name: Symbol)(implicit tasks: mutable.ListBuffer[Task]) extends Task(name) {
  private val subtasks = mutable.ListBuffer[CallTask]()
  private class CallTask (val name: Symbol, val args: Seq[Variable[_]]) {
    private var dependencies = Seq[Symbol]()
    def depends(tasks: Seq[Symbol]): this.type = {
      this.dependencies = tasks
      this
    }
  }
  def call(name: Symbol)(args: Variable[_]*): this.type = {
    subtasks append new CallTask(name, args)
    this
  }
  def call(name: Symbol)(args: Variable[_]*)(dependsOn: Symbol*) = {
    subtasks append new CallTask(name, args).depends(dependsOn)
    this
  }
}
