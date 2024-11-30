package industries.spaceboy.basicComputing.lib.basic

abstract class ExecutionContext {
    abstract fun print(value: Any)
    abstract fun assign(variable: String, value: Any)
    abstract fun getVariable(variable: String): Any
}

