package com.interpreter.parser.ast;

import com.interpreter.parser.variables.NumberValue;
import com.interpreter.parser.variables.Value;
import com.interpreter.parser.variables.Variables;

public class VariableExpression implements Expression {

//    private Value value;
    private String varName;

    public VariableExpression(String varName) {
        this.varName = varName;
    }

//    public VariableExpression(double data) {
//        this.value = new NumberValue(data);
//    }

    @Override
    public Value calculate() {
        if (!Variables.isExists(varName))
            throw new RuntimeException("Variable does not exist!");
        else
            return Variables.getValue(varName);
    }

    @Override
    public String toString() {
        return varName;
    }
}