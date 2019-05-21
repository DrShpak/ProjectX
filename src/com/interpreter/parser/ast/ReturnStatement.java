package com.interpreter.parser.ast;

import com.interpreter.parser.variables.Value;

public class ReturnStatement extends RuntimeException implements Statement {

    private Expression expression;
    private Value result;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        result = expression.calculate();
        throw this;
    }

    public Value getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "break";
    }
}
