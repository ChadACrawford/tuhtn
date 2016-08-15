package edu.utulsa.masters.tuhtn

import edu.utulsa.masters.tuhtn.CompoundTask.CallTask

import scala.collection.mutable

abstract class Arguments

private abstract class Task(val name: Symbol) {
  def precondition(state: CState): UState
}

object Task {

}

abstract case class PrimitiveTask(override val name: Symbol) extends Task(name) {
  def postcondition(state: UState): UState
}

abstract case class CompoundTask(override val name: Symbol) extends Task(name) {
  private val subtasks = mutable.ListBuffer[CallTask]()
  def getSubtasks: Seq[CallTask] = subtasks
}

object CompoundTask {
  class CallTask private (val name: Symbol, val args: Seq[Any])
  class CallTaskWithDependencies private (override val name: Symbol, override val args: Seq[Any], val dependencies: Seq[Symbol]) extends CallTask
  protected def call(name: Symbol)(args: Any*)(implicit task: CompoundTask): Unit = {
    task.subtasks append new CallTask(name, args)
  }
  protected def call(name: Symbol)(args: Any*)(dependencies: Symbol*)(implicit task: CompoundTask): Unit = {
    task.subtasks append new CallTaskWithDependencies(name, args, dependencies)
  }
}
