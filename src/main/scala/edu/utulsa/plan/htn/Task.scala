package edu.utulsa.plan.htn

import scala.collection.mutable

abstract class Task
case class PrimitiveTask() extends Task()
case class CompoundTask() extends Task()
