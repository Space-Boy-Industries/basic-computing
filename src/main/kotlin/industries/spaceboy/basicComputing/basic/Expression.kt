package industries.spaceboy.basicComputing.basic

import industries.spaceboy.basicComputing.basic.context.ExecutionContext

// Expression sealed class (for literal values, variables, and binary operations)
sealed class Expression {
    data class Number(val value: Int) : Expression() {
        override fun evaluate(ctx: ExecutionContext): Any {
            return value
        }
    }
    data class Variable(val name: String) : Expression() {
        override fun evaluate(ctx: ExecutionContext): Any {
            return ctx.getVariable(name) ?: throw RuntimeException("Variable $name not found")
        }
    }

    data class StringLiteral(val value: String) : Expression() {  // New expression type for strings
        override fun evaluate(ctx: ExecutionContext): Any {
            return value
        }
    }

    data class BinaryOp(val left: Expression, val right: Expression, val operator: String) : Expression() {
        override fun evaluate(ctx: ExecutionContext): Any {
            val leftValue = this.left.evaluate(ctx)
            val rightValue = this.right.evaluate(ctx)
            return when (this.operator) {
                "+" -> {
                    // If both operands are strings, concatenate them
                    if (leftValue is String && rightValue is String) {
                        leftValue + rightValue
                    } else if (leftValue is Int && rightValue is Int) {
                        leftValue + rightValue
                    } else if (leftValue is String && rightValue is Int || leftValue is Int && rightValue is String) {
                        leftValue.toString() + rightValue.toString()
                    } else {
                        throw IllegalArgumentException("Addition Operation is not supported for types of: $leftValue and $rightValue")
                    }
                }

                "-" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue - rightValue
                    } else {
                        throw IllegalArgumentException("Cannot subtract non-integer values")
                    }
                }

                "*" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue * rightValue
                    } else {
                        throw IllegalArgumentException("Cannot multiply non-integer values")
                    }
                }

                "/" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue / rightValue
                    } else {
                        throw IllegalArgumentException("Cannot divide non-integer values")
                    }
                }

                "==" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue == rightValue
                    } else {
                        throw IllegalArgumentException("Cannot compare non-integer values")
                    }
                }

                "!=" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue != rightValue
                    } else {
                        throw IllegalArgumentException("Cannot compare non-integer values")
                    }
                }

                "<" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue < rightValue
                    } else {
                        throw IllegalArgumentException("Cannot compare non-integer values")
                    }
                }

                "<=" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue <= rightValue
                    } else {
                        throw IllegalArgumentException("Cannot compare non-integer values")
                    }
                }

                ">" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue > rightValue
                    } else {
                        throw IllegalArgumentException("Cannot compare non-integer values")
                    }
                }

                ">=" -> {
                    if (leftValue is Int && rightValue is Int) {
                        leftValue >= rightValue
                    } else {
                        throw IllegalArgumentException("Cannot compare non-integer values")
                    }
                }

                else -> throw IllegalArgumentException("Unknown operator: ${this.operator}")
            }
        }
    }

    // Evaluate the expression
    abstract fun evaluate(ctx: ExecutionContext): Any
}
