package edu.utulsa.plan.htn

abstract class Operation[T] {
}

object Operation {
  class Assignment[T](val variable: Variable[T], val operation: Operation[T])
}