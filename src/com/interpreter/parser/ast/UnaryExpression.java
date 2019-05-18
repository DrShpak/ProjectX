package com.interpreter.parser.ast;

import com.interpreter.parser.variables.NumberValue;
import com.interpreter.parser.variables.Value;

public class UnaryExpression implements Expression {

    private char operation;
    private Expression exp1;

    public UnaryExpression(char operation, Expression exp1) {
        this.operation = operation;
        this.exp1 = exp1;
    }

    @Override
    public Value calculate(){
        if (operation == '-') {
            return new NumberValue(-exp1.calculate().asDouble());
        }
        return new NumberValue(exp1.calculate().asDouble());
    }

    @Override
    public String toString() {
        return operation + " " + exp1;
    }
}
