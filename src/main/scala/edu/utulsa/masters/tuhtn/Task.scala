package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class Task(val name: Symbol) {
  def require(requirement: )
  def precondition(state: CState): UState
}

object Task {
}

class PrimitiveTask(override val name: Symbol) extends Task(name) {
  override def precondition(state: CState): UState
  def postcondition(state: UState): UState
}

class CompoundTask(override val name: Symbol) extends Task(name) {
  override def precondition(state: CState): UState

  private val subtasks = mutable.ListBuffer[CallTask]()
  def getSubtasks: Seq[CallTask] = subtasks
}

class CallTask (val name: Symbol, val args: Seq[Any])
class CallTaskWithDependencies (override val name: Symbol, override val args: Seq[Any], val dependencies: Seq[Symbol])
  extends CallTask(name, args)

object CompoundTask {
  protected def call(name: Symbol)(args: Any*)(implicit task: CompoundTask): Unit = {
    task.subtasks append new CallTask(name, args)
  }
  protected def call(name: Symbol)(args: Any*)(dependencies: Symbol*)(implicit task: CompoundTask): Unit = {
    task.subtasks append new CallTaskWithDependencies(name, args, dependencies)
  }
}
