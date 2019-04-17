package com.interpreter.parser.ast;

public class ConditionalExpression implements Expression {

    private char operation;
    private Expression exp1, exp2;

    public ConditionalExpression(char operation, Expression exp1, Expression exp2) {
        this.operation = operation;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public double calculate() {
        switch (operation) {
            case '<':
                return exp1.calculate() < exp2.calculate() ? 1 : 0;
            case '>':
                return exp1.calculate() >  exp2.calculate() ? 1 : 0;
            case '=':
                return exp1.calculate() == exp2.calculate() ? 1 : 0;
            default:
                throw new RuntimeException("Неизвестная операция");
        }
    }

    @Override
    public String toString() {
        return exp1.calculate() + " " + operation + " " + exp2.calculate();
    }
}