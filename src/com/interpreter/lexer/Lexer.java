package com.interpreter.lexer;

import com.interpreter.token.Token;
import com.interpreter.token.TokenType;

import java.util.ArrayList;

public class Lexer {

    private String input;
    private int length;
    private int currPos = 0;

    private final String TOKENS_CHAR = "+-*/()";
    private TokenType[] tokenTypes = {
      TokenType.PLUS, TokenType.MINUS,
      TokenType.MULTIPLY, TokenType.DIVISION,
      TokenType.OPEN_BRACKET, TokenType.CLOSE_BRACKET
    };

    private ArrayList<Token> tokens;

    public Lexer(String input) {
        this.input = input;
        length = input.length();
    }

    public ArrayList<Token> tokenize() {
        tokens = new ArrayList<>();
        char currChar = getCurrChar();
        while (currPos < length) {
            if (TOKENS_CHAR.indexOf(currChar) != -1) {
                tokenizeOperator(currChar);
                currChar = nextChar();
            } else if (Character.isDigit(currChar)) {
                tokenizeNumber(currChar);
                currChar = nextChar();
            } else {
                currChar = nextChar();
            }
        }
        return tokens;
    }

    private void tokenizeNumber(char currToken) {
        StringBuilder buffer = new StringBuilder();
        while(Character.isDigit(currToken)) {
            buffer.append(currToken);
            currToken = nextChar();
        }
        addToken(TokenType.NUMBER, buffer.toString());
    }

    private void tokenizeOperator(char currToken) {
        addToken(tokenTypes[TOKENS_CHAR.indexOf(currToken)]);
//        nextChar();
    }

    private char getCurrChar() {
        if (currPos >= length)
            return '\0';
        return input.charAt(currPos);
    }

    private char nextChar() {
        currPos++;
        return getCurrChar();
    }

    private void addToken(TokenType type, String data) {
        tokens.add(new Token(type, data));
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }
}