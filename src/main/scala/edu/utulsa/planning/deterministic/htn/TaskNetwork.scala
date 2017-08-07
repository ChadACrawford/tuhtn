package edu.utulsa.planning.deterministic.htn

import scala.collection.mutable

class TaskNetwork {
  abstract class Node
  case class Branch(nodes: Seq[Node])
  case class Leaf(tasks: Seq[Task])
}
