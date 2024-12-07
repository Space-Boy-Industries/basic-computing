package industries.spaceboy.basicComputing.lib.basic

class Parser(program: String) {
    private val tokenizer = Tokenizer(program)
    private var currentToken: Token? = tokenizer.getNextToken()
    private var currentLine = 0
    private var labels = mutableMapOf<String, Int>()

    private fun consume() {
        currentToken = tokenizer.getNextToken()
    }

    // Parse the entire program (list of statements)
    fun parseProgram(): Program {
        currentLine = 0
        val statements = mutableListOf<Statement>()

        while (currentToken != null) {
            val statement = parseStatement()
            if (statement is Statement.Label) {
                labels[statement.label] = currentLine
            }
            
            statements.add(statement)
            currentLine++
        }


        return Program(statements, labels)
    }

    // Parse a single statement
    private fun parseStatement(): Statement {
        return when (currentToken) {
            is Token.Keyword -> {
                val keyword = (currentToken as Token.Keyword).keyword
                consume() // Only consume the token after processing it

                when (keyword) {
                    "LET" -> parseAssignment()
                    "PRINT" -> parsePrint()
                    "LABEL" -> parseLabel()
                    "GOTO" -> parseGoto()
                    else -> throw IllegalArgumentException("Unknown keyword: $keyword")
                }
            }
            else -> throw IllegalArgumentException("Unexpected token: $currentToken")
        }
    }

    // Parse LET statement (e.g., LET X = 5 + 2)
    private fun parseAssignment(): Statement.Assignment {
        if (currentToken !is Token.Variable) throw IllegalArgumentException("Expected variable")
        val variable = (currentToken as Token.Variable).name
        consume()

        if (currentToken != Token.Equals) throw IllegalArgumentException("Expected '='")
        consume()

        val expression = parseExpression()
        return Statement.Assignment(variable, expression)
    }

    // Parse PRINT statement (e.g., PRINT X)
    private fun parsePrint(): Statement.Print {
        val expression = parseExpression()
        return Statement.Print(expression)
    }

    private fun parseLabel(): Statement.Label {
        if (currentToken !is Token.Variable) throw IllegalArgumentException("Expected variable")
        val label = (currentToken as Token.Variable).name
        consume()
        return Statement.Label(label, currentLine)
    }

    private fun parseGoto(): Statement.Goto {
        if (currentToken !is Token.Variable) throw IllegalArgumentException("Expected variable")
        val label = (currentToken as Token.Variable).name
        consume()
        return Statement.Goto(label)
    }

    // Parse an expression (handles + and - operators)
    private fun parseExpression(): Expression {
        var left = parseTerm()
        while (currentToken is Token.Plus || currentToken is Token.Minus) {
            val operator = currentToken
            consume()
            val right = parseTerm()
            left = when (operator) {
                Token.Plus -> Expression.BinaryOp(left, right, "+")
                Token.Minus -> Expression.BinaryOp(left, right, "-")
                else -> left
            }
        }
        return left
    }

    // Parse a term (handles * and / operators)
    private fun parseTerm(): Expression {
        var left = parseFactor()
        while (currentToken is Token.Star || currentToken is Token.Slash) {
            val operator = currentToken
            consume()
            val right = parseFactor()
            left = when (operator) {
                Token.Star -> Expression.BinaryOp(left, right, "*")
                Token.Slash -> Expression.BinaryOp(left, right, "/")
                else -> left
            }
        }
        return left
    }

    // Parse a factor (handles numbers, variables, and string literals)
    private fun parseFactor(): Expression {
        return when (currentToken) {
            is Token.Number -> {
                val value = (currentToken as Token.Number).value
                consume()
                Expression.Number(value)
            }
            is Token.Variable -> {
                val variableName = (currentToken as Token.Variable).name
                consume()  // Only consume the variable here
                Expression.Variable(variableName)
            }
            is Token.Str -> {
                val value = (currentToken as Token.Str).value
                consume()  // Consume the string literal
                Expression.StringLiteral(value)  // New expression type for strings
            }
            else -> throw IllegalArgumentException("Unexpected factor: $currentToken")
        }
    }
}



