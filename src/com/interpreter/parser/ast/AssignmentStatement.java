package com.interpreter.parser.ast;

import com.interpreter.parser.variables.Variables;

public class AssignmentStatement implements Statement{

    private String variableName;
    private double value;

    public AssignmentStatement(String variableName, double value) {
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public void execute() {
        Variables.addVariable(variableName, value);
    }

    @Override
    public String toString() {
        return variableName + " = " + value;
    }
}
