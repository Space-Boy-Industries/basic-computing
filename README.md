# Basic Computing
WIP mod to add BASIC Computers to Minecraft. The goal is to create a computer mod that captures the nostalgic experience of retro computer systems while also still be accessible/useful to minecraft players.

## K-Basic (Name Pending)
K-Basic is the custom BASIC implementation that is used in this mod. It is a simple BASIC interpreter that is designed to be easy to use and understand.

### Features
- Variables
- Arithmetic Operations
- Boolean Operations
- Basic Control Flow

### Example Code
```BASIC
LET A = 5
LET B = 10
LET C = A + B

IF C > 10 PRINT "C is greater than 10"
IF C < 10 PRINT "C is less than 10"
IF C == 10 PRINT "C is equal to 10"
```

### Grammar
```BNF
<program>       ::= <statement>+ 

<statement>     ::= <assignment>
                | <print>
                | <label>
                | <goto>
                | <if>

<assignment>    ::= "LET" <variable> "=" <expression>

<print>         ::= "PRINT" <expression>

<label>         ::= "LABEL" <variable>

<goto>          ::= "GOTO" <variable>

<if>            ::= "IF" <expression> <statement>

<expression>    ::= <term> { ("+" | "-" | "==" | "!=" | ">" | "<" | ">=" | "<=") <term> }

<term>          ::= <factor> { ("*" | "/") <factor> }

<factor>        ::= <number> 
                | <variable> 
                | <string_literal> 
                | "(" <expression> ")"

<number>        ::= [0-9]+

<variable>      ::= [a-zA-Z_][a-zA-Z0-9_]*

<string_literal> ::= '"' <character>* '"'

<character>     ::= [a-zA-Z0-9!#$%&'()*+,-./:;<=>?@[\\]^_`{|}~]

<operator>      ::= "+" | "-" | "*" | "/" | "=" | "==" | "!=" | ">" | "<" | ">=" | "<="

<left_paren>    ::= "("

<right_paren>   ::= ")"
```
