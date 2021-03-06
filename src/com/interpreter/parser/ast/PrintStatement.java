package com.interpreter.parser.ast;

public class PrintStatement implements Statement {

    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        System.out.print(expression.calculate());
    }

    @Override
    public String toString() {
        return "print " + expression;
    }
}