package industries.spaceboy.basicComputing.lib.basic

sealed class Statement {
    data class Assignment(val variable: String, val expression: Expression) : Statement() {
        override fun execute(executionContext: ExecutionContext) {
            val value = expression.evaluate(executionContext)
            executionContext.assign(variable, value)
        }
    }

    data class Print(val expression: Expression) : Statement() {
        override fun execute(executionContext: ExecutionContext) {
            val value = expression.evaluate(executionContext)
            executionContext.print(value)
        }
    }

    data class Goto(val label: String) : Statement() {
        override fun execute(executionContext: ExecutionContext) {
            executionContext.goto(label)
        }
    }

    // TODO: consider if this needs a statement to go into the statement list.. right now not a big deal and it could enable us to not run sub labels without going to them
    data class Label(val label: String, val line: Int) : Statement() {
        override fun execute(executionContext: ExecutionContext) {
            // Do nothing
        }
    }

    data class IF(val expression: Expression, val statement: Statement) : Statement() {
        override fun execute(executionContext: ExecutionContext) {
            val value = expression.evaluate(executionContext)

            val truthy = (value is Boolean && value)
                    || (value is Int && value != 0)

            if (truthy) {
                statement.execute(executionContext)
            }
        }
    }

    abstract fun execute(executionContext: ExecutionContext)
}
