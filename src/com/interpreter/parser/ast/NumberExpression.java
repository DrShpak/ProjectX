package com.interpreter.parser.ast;

public class NumberExpression implements Expression {

    private double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public double calculate() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}