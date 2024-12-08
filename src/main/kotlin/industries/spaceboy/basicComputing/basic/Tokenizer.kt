package industries.spaceboy.basicComputing.basic

class Tokenizer(private val input: String) {
    private var currentPos = 0

    // A list of keywords in BASIC
    private val keywords = setOf("LET", "PRINT", "IF", "GOTO", "LABEL", "IF")

    fun getNextToken(): Token? {
        if (currentPos >= input.length) return null

        val currentChar = input[currentPos]

        return when {
            currentChar.isWhitespace() -> {
                currentPos++
                getNextToken() // Skip whitespace
            }
            currentChar.isDigit() -> {
                val start = currentPos
                while (currentPos < input.length && input[currentPos].isDigit()) {
                    currentPos++
                }
                val number = input.substring(start, currentPos).toInt()
                Token.Number(number)
            }
            currentChar.isLetter() -> {
                val start = currentPos
                while (currentPos < input.length && input[currentPos].isLetterOrDigit()) {
                    currentPos++
                }
                val word = input.substring(start, currentPos)
                if (word in keywords) {
                    Token.Keyword(word)
                } else {
                    Token.Variable(word)
                }
            }
            currentChar == '=' -> {
                currentPos++
                if (currentPos < input.length && input[currentPos] == '=') {
                    currentPos++
                    Token.DoubleEquals
                } else {
                    Token.Equals
                }
            }
            currentChar == '+' -> {
                currentPos++
                Token.Plus
            }
            currentChar == '-' -> {
                currentPos++
                Token.Minus
            }
            currentChar == '*' -> {
                currentPos++
                Token.Star
            }
            currentChar == '/' -> {
                currentPos++
                Token.Slash
            }
            currentChar == '(' -> {
                currentPos++
                Token.LeftParen
            }
            currentChar == ')' -> {
                currentPos++
                Token.RightParen
            }
            currentChar == '\n' || currentChar == '\r' -> {
                currentPos++
                Token.EndOfLine
            }
            currentChar == '"' -> {
                val start = currentPos
                currentPos++
                while (currentPos < input.length && input[currentPos] != '"') {
                    currentPos++
                }
                val str = input.substring(start + 1, currentPos)
                currentPos++ // Skip the closing quote
                Token.Str(str)
            }
            currentChar == '<' -> {
                currentPos++
                if (currentPos < input.length && input[currentPos] == '=') {
                    currentPos++
                    Token.LessThanOrEqual
                } else {
                    Token.LessThan
                }
            }
            currentChar == '>' -> {
                currentPos++
                if (currentPos < input.length && input[currentPos] == '=') {
                    currentPos++
                    Token.GreaterThanOrEqual
                } else {
                    Token.GreaterThan
                }
            }
            currentChar == '!' -> {
                currentPos++
                if (currentPos < input.length && input[currentPos] == '=') {
                    currentPos++
                    Token.NotEquals
                } else {
                    throw IllegalArgumentException("Unexpected character: $currentChar")
                }
            }

            // TODO: probably make internal set of errors to throw that can be easily output to the user
            else -> throw IllegalArgumentException("Unexpected character: $currentChar")
        }
    }
}
