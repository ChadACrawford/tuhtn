package edu.utulsa.plan.htn

abstract class Condition {
  def eval(args: Arguments): Boolean
}

object Condition {
  class AND(val conditions: Condition*) extends Condition {
    override def eval(args: Arguments): Boolean = {
      conditions.map(_.eval(args)).reduce(_ && _)
    }
  }
}