package industries.spaceboy.basicComputing.lib.basic

sealed class Token {
    data class Number(val value: Int) : Token()
    data class Variable(val name: String) : Token()
    data class Keyword(val keyword: String) : Token()
    data class Str(val value: String) : Token()  // New token type for string literals
    data object Equals : Token()
    data object DoubleEquals : Token()
    data object NotEquals : Token()
    data object GreaterThan : Token()
    data object LessThan : Token()
    data object GreaterThanOrEqual : Token()
    data object LessThanOrEqual : Token()
    data object Plus : Token()
    data object Minus : Token()
    data object Star : Token()
    data object Slash : Token()
    data object LeftParen : Token()
    data object RightParen : Token()
    data object EndOfLine : Token()
}
