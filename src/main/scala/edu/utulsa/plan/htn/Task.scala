package edu.utulsa.plan.htn

abstract class Task
case class PrimitiveTask() extends Task()
case class CompoundTask() extends Task()
