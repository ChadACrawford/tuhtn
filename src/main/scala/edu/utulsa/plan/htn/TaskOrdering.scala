package edu.utulsa.plan.htn

class TaskOrdering(val root: TaskOrdering.Node) {
}

object TaskOrdering {
  abstract class Node
  case class Branch(nodes: Seq[Node])
  case class Leaf(tasks: Seq[Task])
}
