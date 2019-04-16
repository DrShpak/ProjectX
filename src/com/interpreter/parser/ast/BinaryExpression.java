package com.interpreter.parser.ast;

public class BinaryExpression implements Expression {

    private char operation;
    private Expression exp1, exp2;

    public BinaryExpression(char operation, Expression exp1, Expression exp2) {
        this.operation = operation;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public double calculate() {
        switch (operation) {
            case '+':
                return exp1.calculate() + exp2.calculate();
            case '-':
                return exp1.calculate() - exp2.calculate();
            case '*':
                return exp1.calculate() * exp2.calculate();
            case '/':
                return exp1.calculate() / exp2.calculate();
            default:
                throw new RuntimeException("Неизвестная операция");
        }
    }

    @Override
    public String toString() {
        return exp1 + " " + operation + " " + exp2;
    }
}