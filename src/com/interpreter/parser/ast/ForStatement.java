package com.interpreter.parser.ast;

public class ForStatement implements Statement {

    private Statement initialization;
    private Expression termination;
    private Statement increment;
    private Statement block;

    public ForStatement(Statement initialization, Expression termination, Statement increment, Statement block) {
        this.initialization = initialization;
        this.termination = termination;
        this.increment = increment;
        this.block = block;
    }

    @Override
    public void execute() {
        for (initialization.execute(); termination.calculate().asDouble() != 0; increment.execute()) {
            block.execute();
        }
    }

    @Override
    public String toString() {
        return "for " + initialization + "; " + termination + "; " + increment + block;
    }
}
