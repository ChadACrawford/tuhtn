package edu.utulsa.plan.htn

/**
  * Represents a plan carried out
  */
class Plan {
}

object Plan {
  abstract class Node
  case class Branch(method: Method, branches: Seq[Node]) extends Node
  case class Leaf(operator: Operator) extends Node
}