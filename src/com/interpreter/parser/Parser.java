package com.interpreter.parser;

import com.interpreter.parser.ast.*;
import com.interpreter.parser.variables.Variables;
import com.interpreter.token.Token;
import com.interpreter.token.TokenType;

import java.util.ArrayList;

public class Parser {

    private static final Token EOF = new Token(TokenType.EOF);
    private ArrayList<Token> tokens;
    private int currPos;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<Statement> parse() {
        ArrayList<Statement> result = new ArrayList<>();
        while (!match(TokenType.EOF)) {
            result.add(statement());
        }
        return result;
    }

    private Statement statement() {
        return assignmentStatement();
    }
    /**
     *  Метод определения операторов по токенам и рассчёта значения соотвествующих операндов.
     *  Созданный оператор сразу "извлекается", помещая переменную в базу данных.
     *  Если набор токенов не соответсвует ни одному оператору, исключение.
     *
     * @return оператор, включающий в себя результат своей работы.
     */
    private Statement assignmentStatement() {
        Token firstOperand = getCurrToken();
        currPos++;
        if (firstOperand.getType() == TokenType.WORD && match(TokenType.EQUAL)){
            Statement asgStatement = new AssignmentStatement(firstOperand.getData(), expression().calculate());
            asgStatement.execute();
            return asgStatement;
        }
        throw new RuntimeException("Unknown statement");
    }

    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression result = multiplicative();

        while (true) {
            if (match(TokenType.PLUS)) {
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)) {
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression multiplicative() {
        Expression result = unary();

        while (true) {
            // 2 * 6 / 3
            if (match(TokenType.MULTIPLY)) {
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenType.DIVISION)) {
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression unary() {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        if (match(TokenType.PLUS)) {
            return primary();
        }
        return primary();
    }

    //парсим числа, строки и тд
    //самый нижний уровень в рекурсивном спуске
    private Expression primary() {
        Token current = getCurrToken();
        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(current.getData()));
        }
        /*if (match(TokenType.HEX_NUMBER)) {
            return new NumberExpression(Long.parseLong(current.getData(), 16));
        }*/
        if (match(TokenType.WORD)) {
            return new VariableExpression(Variables.getValue(current.getData()));
        }
        if (match(TokenType.OPEN_BRACKET)) {
            Expression result = expression();
            match(TokenType.CLOSE_BRACKET);
            return result;
        }
        throw new RuntimeException("Unknown expression");
    }

    private Token consume(TokenType type) {
        Token current = getCurrToken();
        if (type != current.getType()) throw new RuntimeException("Token " + current + " doesn't match " + type);
        currPos++;
        return current;
    }

    private Token getCurrToken() {
        if (currPos >= tokens.size())
            return EOF;
        return tokens.get(currPos);
    }

    private boolean match(TokenType type) {
        Token currToken = getCurrToken();
        if (type != currToken.getType()) {
            return false;
        }

        currPos++;
        return true;
    }
}
