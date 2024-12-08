package industries.spaceboy.basicComputing.basic.context

import industries.spaceboy.basicComputing.basic.Program

class StdOutExecutionContext(program: Program) : ExecutionContext(program) {
    private val variables = mutableMapOf<String, Any>()

    override fun print(value: Any) {
        println(value)
    }
}
