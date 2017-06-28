package edu.utulsa.masters.tuhtn

class Method(val task: CompoundTask) {
  var precondition: Precondition = null
  var tn: TaskNetwork = null

  def ?(precondition: Precondition): this.type = {
    this.precondition = precondition
    this
  }
  def ->(tn: TaskNetwork): this.type = {
    this.tn = tn
    this
  }
}
