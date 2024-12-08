package industries.spaceboy.basicComputing.basic.context

import industries.spaceboy.basicComputing.basic.Program

class MockExecutionContext(program: Program) : ExecutionContext(program) {
    private var stdout: String = ""

    override fun print(value: Any) {
        stdout += value.toString() + "\n"
    }

    fun getStdout(): String {
        return stdout
    }
}