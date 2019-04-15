package com.interpreter.parser.ast;

public class UnaryExpression implements Expression {

    private char operation;
    private Expression exp1;

    public UnaryExpression(char operation, Expression exp1) {
        this.operation = operation;
        this.exp1 = exp1;
    }

    @Override
    public double calculate() throws Exception {
        switch (operation) {
            case '-':
                return -exp1.calculate();
            case '+':
            default:
                return exp1.calculate();
        }
    }

    @Override
    public String toString() {
        return operation + " " + exp1;
    }
}
