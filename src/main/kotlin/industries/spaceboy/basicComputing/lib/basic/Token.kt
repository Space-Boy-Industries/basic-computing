package industries.spaceboy.basicComputing.lib.basic

sealed class Token {
    data class Number(val value: Int) : Token()
    data class Variable(val name: String) : Token()
    data class Keyword(val keyword: String) : Token()
    data class Str(val value: String) : Token()  // New token type for string literals
    object Equals : Token()
    object Plus : Token()
    object Minus : Token()
    object Star : Token()
    object Slash : Token()
    object LeftParen : Token()
    object RightParen : Token()
    object EndOfLine : Token()
}
