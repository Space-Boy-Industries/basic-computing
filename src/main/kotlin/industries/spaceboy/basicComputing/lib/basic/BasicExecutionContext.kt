package industries.spaceboy.basicComputing.lib.basic

class BasicExecutionContext(program: Program) : ExecutionContext(program) {
    private val variables = mutableMapOf<String, Any>()

    override fun print(value: Any) {
        println(value)
    }
}
