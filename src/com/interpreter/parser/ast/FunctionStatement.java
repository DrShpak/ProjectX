package com.interpreter.parser.ast;

public class FunctionStatement implements Statement {


    private FunctionalExpression function;

    public FunctionStatement(FunctionalExpression function) {
        this.function = function;
    }

    @Override
    public void execute() {
        function.calculate();
    }


}
