package edu.utulsa.plan.htn

class Variable[T]()


class FreeVariable[T](val flags: Seq[FVFlag[T]] = null) extends Variable[T]()
class FVFlag[T]


/**
  * Variable associated with the state of the system.
  * @param default Initial value that the variable has.
  * @tparam T The type of the variable.
  */
class Predicate[T](val default: T) extends Variable[T]()
