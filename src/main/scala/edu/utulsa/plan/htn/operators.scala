package edu.utulsa.plan.htn

object operators {
  /**
    * General class for evaluating an expression in which structural information can be parsed.
    * @tparam T The type that the expression returns.
    */
  abstract class Expression[T] {
    /**
      * Evaluates the expression on a set of argumentss.
      * @param args Arguments object.
      * @return The value generates from the expression.
      */
    def apply(args: Arguments): T

    /**
      * The set of variables that this expression requires. This may be useful in the future for determining
      * if certain expressions intersect with each other.
      * @return Set of all variables referenced in this expression.
      */
    def scope: Set[Variable[_]]
  }

  trait Expr1Param {
    def expr: Expression[_]
    lazy val scope: Set[Variable[_]] = expr.scope
  }
  trait Expr2Param {
    def expr1: Expression[_]
    def expr2: Expression[_]
    lazy val scope: Set[Variable[_]] = expr1.scope ++ expr2.scope
  }

  case class Literal[T](variable: Variable[T]) extends Expression[T] {
    override def apply(args: Arguments): T = args(variable)
    override lazy val scope = Set(variable)
  }
  case class Constant[T](value: T) extends Expression[T] {
    override def apply(args: Arguments): T = value
    override lazy val scope = Set()
  }
  case class Custom[T](f: (Arguments) => T, scope: Set[Variable[_]] = Set()) extends Expression[T] {
    override def apply(args: Arguments): T = f(args)
  }

  object logic {
    private type Expr = Expression[Boolean]

    case class And(expr1: Expr, expr2: Expr) extends Expr with Expr2Param {
      override def apply(args: Arguments): Boolean = expr1(args) && expr2(args)
    }
    case class Or(expr1: Expr, expr2: Expr) extends Expr with Expr2Param {
      override def apply(args: Arguments): Boolean = expr1(args) || expr2(args)
    }
    case class Not(expr: Expr) extends Expr with Expr1Param {
      override def apply(args: Arguments): Boolean = !expr(args)
    }

    // Integer ops
    case class GreaterThan(expr1: Expression[Int], expr2: Expression[Int]) extends Expr with Expr2Param {
      override def apply(args: Arguments): Boolean = expr1(args) > expr2(args)
    }
    case class GreaterThanOrEqual(expr1: Expression[Int], expr2: Expression[Int]) extends Expr with Expr2Param {
      override def apply(args: Arguments): Boolean = expr1(args) >= expr2(args)
    }
    case class IntEquals(expr1: Expression[Int], expr2: Expression[Int]) extends Expr with Expr2Param {
      override def apply(args: Arguments): Boolean = expr1(args) == expr2(args)
    }
  }


  object arithmetic {
    private type Expr = Expression[Int]

    case class Add(expr1: Expr, expr2: Expr) extends Expr with Expr2Param {
      override def apply(args: Arguments): Int = expr1(args) + expr2(args)
    }
    case class Subtract(expr1: Expr, expr2: Expr) extends Expr with Expr2Param {
      override def apply(args: Arguments): Int = expr1(args) - expr2(args)
    }
    case class Multiply(expr1: Expr, expr2: Expr) extends Expr with Expr2Param {
      override def apply(args: Arguments): Int = expr1(args) * expr2(args)
    }
    case class Divide(expr1: Expr, expr2: Expr) extends Expr with Expr2Param {
      override def apply(args: Arguments): Int = expr1(args) / expr2(args)
    }
  }
}