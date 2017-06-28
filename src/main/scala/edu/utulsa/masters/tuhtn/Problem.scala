package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class Problem {
  implicit val stateTemplate = new StateTemplate()
  implicit val tasks = mutable.HashMap[Symbol, Seq[Task]]()
}
