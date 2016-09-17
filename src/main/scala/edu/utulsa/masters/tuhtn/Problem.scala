package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class Problem {
  implicit val initialState = State()
  implicit val tasks = mutable.HashMap[Symbol, Seq[Task]]()

  def tmatch(name: Symbol, args: Seq[Any]): Seq[Task] = {
    tasks(name).filter(_.valid(args))
  }
}
