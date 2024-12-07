package industries.spaceboy.basicComputing.lib.basic

class BasicInterpreter(
    private val executionContext: ExecutionContext,
) {
    private fun executeNext(): Boolean {
        if (executionContext.pc >= executionContext.program.statements.size) {
            return true
        }

        val statement = executionContext.program.statements[executionContext.pc]
        statement.execute(executionContext)
        executionContext.pc++

        return false
    }

    fun executeAll() {
        var finished = executeNext()
        while (!finished) {
            finished = executeNext()
        }
    }
}
