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

    // Define an abstract execute method that forces subclasses to implement their own logic
    abstract fun execute(executionContext: ExecutionContext)
}
