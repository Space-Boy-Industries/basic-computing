package industries.spaceboy.basicComputing.lib.basic

abstract class ExecutionContext(val program: Program) {
    private val variables = mutableMapOf<String, Any>()
    var pc = 0

    open fun goto(label: String) {
        pc = program.labels[label] ?: throw IllegalArgumentException("Label not found: $label")
        pc--; // decrement to account for the increment that happens in executeNext
    }

    open fun assign(variable: String, value: Any) {
        variables[variable] = value
    }

    open fun getVariable(variable: String): Any {
        return variables[variable] ?: throw IllegalArgumentException("Variable not found: $variable")
    }

    // these need to implemented for the specific system its running on (console vs in-game computer)
    abstract fun print(value: Any)
}
