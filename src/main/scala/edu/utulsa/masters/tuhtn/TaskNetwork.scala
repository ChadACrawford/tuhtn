package edu.utulsa.masters.tuhtn

import scala.collection.mutable

abstract class Node(var parent: Node)
case class TaskNode(task: Task)(override var parent: BlockNode) extends Node(parent) {
  /**
    * Replaces this node (presumably, a compound task) with a new Task Network in order to preserve order.
    * @param tn The task network.
    */
  def replace(tn: TaskNetwork) = {
    parent.nodes append tn.root
    tn.root.parent = parent
    parent.nodes --= Seq(this)
    parent = null
  }
}
case class BlockNode(nodes: mutable.ListBuffer[Node] = mutable.ListBuffer())(override var parent: BranchNode)
  extends Node(parent)
case class BranchNode(blocks: mutable.ListBuffer[BlockNode] = mutable.ListBuffer())(override var parent: BlockNode)
  extends Node(parent)

class TaskNetwork {
  val root = BranchNode()(null)

  def last = root.blocks.last

  private def split: Unit =
    root.blocks append BlockNode()(root)

  def --- = split
  def ---- = split
  def ----- = split
  def ------ = split
  def ------- = split

  protected def add(task: Task): Unit =
    last.nodes append TaskNode(task)(last)
}
