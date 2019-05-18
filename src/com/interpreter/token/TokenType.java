package com.interpreter.token;

public enum TokenType {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVISION,
    EQUAL,
    VARIABLE,
    ASSIGMENT_OPERATOR,
    LT, // <
    GT, // >
    LE, // <=
    GE, // >=
    NE, // NotEqual !=
    EOF,

    IF,
    ELSE,
    OPEN_BRACKET,
    CLOSE_BRACKET,

    BAR,
    BARBAR,
    AMP,
    AMPAMP,


    //kew words
    NUMBER,
    PRINT,
    TEXT
}