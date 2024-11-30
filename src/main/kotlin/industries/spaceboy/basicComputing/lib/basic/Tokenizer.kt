package industries.spaceboy.basicComputing.lib.basic

class Tokenizer(private val input: String) {
    private var currentPos = 0

    // A list of keywords in BASIC
    private val keywords = setOf("LET", "PRINT", "IF", "GOTO")

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
                Token.Equals
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

            else -> throw IllegalArgumentException("Unexpected character: $currentChar")
        }
    }
}
