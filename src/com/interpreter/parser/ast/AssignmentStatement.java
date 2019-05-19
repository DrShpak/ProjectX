package com.interpreter.parser.ast;

import com.interpreter.parser.variables.Variables;

public class AssignmentStatement implements Statement {

    private String variableName;
    private Expression value;

    public AssignmentStatement(String variableName, Expression value) {
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public void execute() {
        Variables.addVariable(variableName, value.calculate());
    }

    @Override
    public String toString() {
        return variableName + " = " + value.calculate();
    }
}
