package com.interpreter.parser.ast;

public class VariableExpression implements Expression {

    private double data;

    public VariableExpression(double data) {
        this.data = data;
    }

    @Override
    public double calculate() {
        return data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
