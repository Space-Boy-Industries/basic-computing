package industries.spaceboy.basicComputing.basic.context

import industries.spaceboy.basicComputing.basic.Program

class ComputerExecutionContext(
    private val printCallback: (value: Any) -> Unit,
    program: Program
): ExecutionContext(program) {
    override fun print(value: Any) {
        printCallback(value)
    }
}