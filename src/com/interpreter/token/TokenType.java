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
    EXCL,
    EOF,

    IF,
    ELSE,

    OPEN_BRACKET, // (
    CLOSE_BRACKET, // )
    LBRACE, // {
    RBRACE, // }


    BAR,
    BARBAR,
    AMP,
    AMPAMP,

    WHILE,
    FOR,
    BREAK,
    CONTINUE,

    DEF,
    RETURN,

    //kew words
    NUMBER,
    PRINT,
    TEXT,

    SEPARATOR,
    COMMA// ; для цилка for
}