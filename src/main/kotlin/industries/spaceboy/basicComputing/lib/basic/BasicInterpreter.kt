package industries.spaceboy.basicComputing.lib.basic

class BasicInterpreter(
    private val executionContext: ExecutionContext,
    private var statements: List<Statement>
) {
    private var pc = 0

    fun executeNext(): Boolean {
        if (pc >= statements.size) {
            return true
        }

        val statement = statements[pc]
        statement.execute(executionContext)
        pc++

        return false
    }

    fun executeAll() {
        var finished = executeNext()
        while (!finished) {
            finished = executeNext()
        }
    }

    fun reset() {
        pc = 0
    }

    fun setVariable(name: String, value: Any) {
        executionContext.assign(name, value)
    }

    fun getVariable(name: String): Any? {
        return executionContext.getVariable(name)
    }

    fun appendStatements(statements: List<Statement>) {
        this.statements += statements
    }
}
