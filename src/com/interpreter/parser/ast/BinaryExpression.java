package com.interpreter.parser.ast;

import com.interpreter.parser.variables.NumberValue;
import com.interpreter.parser.variables.StringValue;
import com.interpreter.parser.variables.Value;

public class BinaryExpression implements Expression {

    private char operation;
    private Expression exp1, exp2;

    public BinaryExpression(char operation, Expression exp1, Expression exp2) {
        this.operation = operation;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Value calculate() {
        final Value value1 = exp1.calculate();
        final Value value2 = exp2.calculate();
        if (value1 instanceof StringValue) {
            final String string1 = value1.asString();
            final String string2 = value2.asString();
            switch (operation) {
                case '*': {
                    final int iterations = (int) value2.asDouble();
                    final StringBuilder builder = new StringBuilder();
                    builder.append(String.valueOf(string1).repeat(Math.max(0, iterations)));
                    return new StringValue(builder.toString());
                }
                case '+': {
                    return new StringValue(string1 + string2);
                }

            }
        }

        final double number1 = value1.asDouble();
        final double number2 = value2.asDouble();
        switch (operation) {
            case '+':
                return new NumberValue(number1 + number2);
            case '-':
                return new NumberValue(number1 - number2);
            case '*':
                return new NumberValue(number1 * number2);
            case '/':
                if (exp2.calculate().asDouble() == 0) {
                    throw new RuntimeException("Division by zero!");
                } else
                    return new NumberValue(number1 / number2);
            default:
                throw new RuntimeException("Неизвестная операция");
        }
    }

    @Override
    public String toString() {
        return exp1 + " " + operation + " " + exp2;
    }
}