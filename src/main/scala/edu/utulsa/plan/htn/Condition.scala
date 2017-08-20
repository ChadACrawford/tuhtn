package edu.utulsa.plan.htn

abstract class Condition {
  def apply(args: Arguments): Boolean
}

object Condition {
  class And(val c1: Condition, val c2: Condition) extends Condition {
    override def apply(args: Arguments): Boolean = c1(args) && c2(args)
  }
  class Or(val c1: Condition, val c2: Condition) extends Condition {
    override def apply(args: Arguments): Boolean = c1(args) || c2(args)
  }


  class or(val conditions: Condition*) extends Condition {
    override def eval(args: Arguments): Boolean = {
      conditions.map(_.eval(args)).reduce(_ || _)
    }
  }
}