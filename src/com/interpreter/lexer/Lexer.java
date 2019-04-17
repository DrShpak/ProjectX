package com.interpreter.lexer;

import com.interpreter.token.Token;
import com.interpreter.token.TokenType;

import java.util.ArrayList;

public class Lexer {
    private static final String ERROR_WRONG_NUMBER = "После числа должен следовать пробел, конец строки или специальный символ.";

    private String input;
    private int length;
    private int currPos = 0;

    private final String WORDS_PATTERN = "^[_A-Za-z][_A-Za-z1-9]*$";
    private final String END_CHAR = " \0\n\r\t";
    private final String TOKENS_CHAR = "+-*/()=<>";
    private TokenType[] tokenTypes = {
      TokenType.PLUS, TokenType.MINUS,
      TokenType.MULTIPLY, TokenType.DIVISION,
      TokenType.OPEN_BRACKET, TokenType.CLOSE_BRACKET,
      TokenType.EQUAL, TokenType.LT, TokenType.GT
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
//                Метод tokenizeNumber после добавления всего числа в токе, вызывает nextChar() ещё раз.
//                  => после этого метода не требуется вызов следующего символа, его может вернуть tokenizeNumber()
                currChar = tokenizeNumber(currChar);
            }
            else {
                currChar = tokenizeWord(currChar);
            }
        }
        return tokens;
    }

    /**
     * Метод добавляет соответсвующий токен, если встречено ключевое слово, иначе
     * метод добавляет токен типа "слово", если символ, с которого оно начинается,
     * удовлетворяет шаблону слова. По окончании цикла, из буфера "Word" удаляется последний символ,
     * который к слову уже не относится. Этот же символ остаётся в currChar, который и возвращает метод.
     *
     * @param currChar - символ, с которого начинается слово.
     * @return - следующий за словом символ.
     */
    private char tokenizeWord(char currChar) {
        StringBuilder word = new StringBuilder();
        do{
            word.append(currChar);
            currChar = nextChar();
        }while (word.toString().matches(WORDS_PATTERN));

        word.deleteCharAt(word.length()-1);
        if (word.toString().equals("if"))
            addToken(TokenType.IF);
        else if (word.toString().equals("else"))
            addToken(TokenType.ELSE);
        else if (word.length() > 0)
            addToken(TokenType.WORD, word.toString());
        return currChar;
    }

    private char tokenizeNumber(char currToken) {
        StringBuilder buffer = new StringBuilder();
        while(Character.isDigit(currToken)) {
            buffer.append(currToken);
            currToken = nextChar();
        }
//          Если после числа не пробел и не специальный символ, исключение.
//          currToken будет равен 0(\0), если это пустой (null) символ. Такое произойдёт,
//          например, когда данное число - последней символ в коде на нашем языке.
        if (currToken != ' ' && TOKENS_CHAR.indexOf(currToken) == -1 && END_CHAR.indexOf(currToken) == -1)
            throw new RuntimeException(ERROR_WRONG_NUMBER + " (" + buffer + currToken + ')');
        addToken(TokenType.NUMBER, buffer.toString());
        return currToken;
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