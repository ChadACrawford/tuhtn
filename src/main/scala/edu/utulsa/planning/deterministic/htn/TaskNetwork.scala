package edu.utulsa.planning.deterministic.htn

class TaskNetwork(val root: Node) {
}

object TaskNetwork {
  abstract class Node
  case class Branch(nodes: Seq[Node])
  case class Leaf(tasks: Seq[Task])
}
