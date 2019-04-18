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
        if (match(TokenType.IF))
            return ifElse();
        return assignmentStatement();
    }

    /**
     * Метод определения условного оператора.
     * Сначала рассчитывается выражение условия, затем создаётся тело if,
     * после, если за телом if следует токен типа "ELSE", формируется тело блока else,
     * иначе ему присваевается null и оно исполняться не будет.
     * P.S. что-то упустил момент, где и когда правильно "извлекать" результат работы операторов,
     * поэтому делаю сразу после их создания.
     *
     * @return оператор, включающий в себя результат своей работы.
     */
    private Statement ifElse() {
        Expression condition = expression();
        Statement ifStatement = statement();
        Statement elseStatement = match(TokenType.ELSE) ? statement() : null;
        Statement result = new IfStatement(condition, ifStatement, elseStatement);
        result.execute();
        return result;
    }

    /**
     * Метод определения оператора присваивания.
     * Созданный оператор сразу "извлекается", помещая переменную в базу данных.
     * Если набор токенов не соответсвует оператору, исключение.
     *
     * @return оператор, включающий в себя результат своей работы.
     */
    private Statement assignmentStatement() {
        Token firstOperand = getCurrToken();
        currPos++;
        if (firstOperand.getType() == TokenType.VARIABLE && match(TokenType.ASSIGMENT_OPERATOR)) {
            Statement asgStatement = new AssignmentStatement(firstOperand.getData(), expression());
            asgStatement.execute();
            return asgStatement;
        }
        throw new RuntimeException("Unknown statement");
    }

    private Expression expression() {
        return condition();
    }

    /**
     * Метод создаёт выражение, содержащее некоторое условие.
     * Приоритет его расчёта ниже, чем у операции сложения.
     *
     * @return условный оператор.
     */
    private Expression condition() {
        Expression result = additive();

        boolean isTrue = true;
        while (isTrue) {
            TokenType type = getCurrToken().getType();
            switch (type) {
                case EQUAL:
                    nextToken();
                    result = new ConditionalExpression("==", result, multiplicative());
                    break;
                case LT:
                    nextToken();
                    result = new ConditionalExpression("<", result, multiplicative());
                    break;
                case GT:
                    nextToken();
                    result = new ConditionalExpression(">", result, multiplicative());
                    break;
                case LE:
                    nextToken();
                    result = new ConditionalExpression("<=", result, multiplicative());
                    break;
                case GE:
                    nextToken();
                    result = new ConditionalExpression(">=", result, multiplicative());
                    break;
                case NE:
                    nextToken();
                    result = new ConditionalExpression("!=", result, multiplicative());
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }

        /*while (true) {

            if (match(TokenType.EQUAL)) {
                result = new ConditionalExpression("==", result, multiplicative());
                continue;
            }
            if (match(TokenType.LT)) {
                result = new ConditionalExpression("<", result, multiplicative());
                continue;
            }
            if (match(TokenType.GT)) {
                result = new ConditionalExpression(">", result, multiplicative());
                continue;
            }
            if (match(TokenType.LE)) {
                result = new ConditionalExpression("<=", result, multiplicative());
                continue;
            }
            if (match(TokenType.GE)) {
                result = new ConditionalExpression(">=", result, multiplicative());
                continue;
            }
            if (match(TokenType.NE)) {
                result = new ConditionalExpression("!=", result, multiplicative());
                continue;
            }
            break;
        }*/
        return result;
    }

    private void nextToken() {
        currPos++;
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
        if (match(TokenType.VARIABLE)) {
            return new VariableExpression(Variables.getValue(current.getData()));
        }
        if (match(TokenType.OPEN_BRACKET)) {
            Expression result = expression();
            match(TokenType.CLOSE_BRACKET);
            return result;
        }
        System.out.println("ПЛОХОЙ ТОКЕН = " + current + " position " + currPos);
        throw new RuntimeException("Unknown expression");
    }

    /*private Token consume(TokenType type) {
        Token current = getCurrToken();
        if (type != current.getType()) throw new RuntimeException("Token " + current + " doesn't match " + type);
        currPos++;
        return current;
    }*/

    /**
     * метод для взятия текущего токена
     * в случае, если вышли за границы списка токенов - выбрасывается исключение
     *
     * @return текущий токен
     */
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