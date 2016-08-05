package edu.utulsa.masters.tuhtn

import scala.collection.mutable

private abstract class Task(val name: String) {
  def precondition(state: State)
  def postcondition(state: State)
}

abstract case class PrimitiveTask(override val name: String) extends Task(name)

abstract case class CompoundTask(override val name: String) extends Task(name) {
  override def precondition(implicit state: State)
  override def postcondition(implicit state: State): State = ???

  private val tasks = mutable.ListBuffer[Task]()

  def addTask(task: Task): this.type = {
    tasks.append(task)
    this
  }
}
