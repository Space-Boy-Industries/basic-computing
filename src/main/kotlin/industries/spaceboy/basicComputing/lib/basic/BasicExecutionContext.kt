package industries.spaceboy.basicComputing.lib.basic

class BasicExecutionContext: ExecutionContext() {
    private val variables = mutableMapOf<String, Any>()

    override fun print(value: Any) {
        println(value)
    }

    override fun assign(variable: String, value: Any) {
        variables[variable] = value
    }

    override fun getVariable(variable: String): Any {
        return variables[variable] ?: throw IllegalArgumentException("Variable not found: $variable")
    }
}
