package industries.spaceboy.basicComputing.lib.basic

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

/**
 * This is more like end-to-end testing to make sure the entire basic interpreter works as expected.
 */
class BasicTest {
    @Test
    fun testPrint() {
        val ctx = MockExecutionContext(Parser("""
            PRINT "Hello, World!"
        """.trimIndent()).parseProgram())
        val interpreter = BasicInterpreter(ctx)

        interpreter.executeAll()

        assertEquals(ctx.getStdout(), "Hello, World!\n", "Should print Hello, World!")
    }

    @Test
    fun testAssign() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            PRINT a
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getVariable("a"), 42)
        assertEquals(ctx.getStdout(), "42\n", "Should print the value of a")
    }

    @Test
    fun testAdd() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            LET b = 23
            LET c = a + b
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getVariable("c"), 65, "Should be 65")
    }

    @Test
    fun testSub() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            LET b = 23
            LET c = a - b
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getVariable("c"), 19, "Should be 19 because of subtraction")
    }

    @Test
    fun testMul() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            LET b = 23
            LET c = a * b
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getVariable("c"), 966, "Should be 966 because of integer multiplication")
    }

    @Test
    fun testDiv() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            LET b = 23
            LET c = a / b
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getVariable("c"), 1, "Should be 1 because of integer division")
    }

    @Test
    fun testLabel() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            GOTO printvar
            LET a = 23
            LABEL printvar
            PRINT a
            PRINT "Done"
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getVariable("a"), 42, "Should not execute the second LET statement")
        assertEquals(ctx.getStdout(),"42\nDone\n", "Should print the first value of a")
    }

    @Test
    fun testSyntaxError() {
        assertThrows<IllegalArgumentException> {
            val ctx = MockExecutionContext(Parser("""
                FAKE "Hello, World!"
            """.trimIndent()).parseProgram())
        }
    }

    @Test
    fun testVarUndefinedError() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            PRINT b
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        assertThrows<IllegalArgumentException> {
            interpreter.executeAll()
        }
    }

    @Test
    fun testSimpleTrueIf() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            IF a == 42 PRINT "Yes"
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getStdout(), "Yes\n", "Should print Yes")
    }

    @Test
    fun testSimpleFalseIf() {
        val ctx = MockExecutionContext(Parser("""
            LET a = 42
            IF a == 23 PRINT "Yes"
        """.trimIndent()).parseProgram())

        val interpreter = BasicInterpreter(ctx)
        interpreter.executeAll()

        assertEquals(ctx.getStdout(), "", "Should not print anything")
    }

    @Test
    fun testInvalidIfSyntax() {
        // IF Syntax is IF <expression> <statement>
        // Should throw because x + y is an expression, not a statement
        assertThrows<IllegalArgumentException> {
            Parser("""
                LET a = 42
                LET x = 23
                LET y = 42
                IF a == 23 x + y
            """.trimIndent()).parseProgram()
        }
    }

    @Test
    fun testInvalidIfSyntax2() {
        // IF Syntax is IF <expression> <statement>
        // Should throw because there is no statement after the IF expression
        assertThrows<IllegalArgumentException> {
            Parser("""
                LET a = 42
                IF a == 23
            """.trimIndent()).parseProgram()
        }
    }
}

class MockExecutionContext(program: Program) : ExecutionContext(program) {
    private var stdout: String = ""

    override fun print(value: Any) {
        stdout += value.toString() + "\n"
    }

    fun getStdout(): String {
        return stdout
    }
}