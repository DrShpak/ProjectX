package com.interpreter.token;

public enum TokenType {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVISION,
    EQUAL,
    ASSIGMENT_OPERATOR,
    LT, // <
    GT, // >
    LE, // <=
    GE, // >=
    NE, // NotEqual !=
    EOF,

    NUMBER,
    WORD,
    IF,
    ELSE,
    OPEN_BRACKET,
    CLOSE_BRACKET
}