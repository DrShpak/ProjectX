package com.interpreter.parser.ast;

import com.interpreter.parser.variables.Function;
import com.interpreter.parser.variables.NumberValue;
import com.interpreter.parser.variables.Value;

import java.util.List;

/*
класс для описания функций, созданных программистом/юзером
 */
public class UserFunctionDefine implements Function {

    private List<String> args;
    private Statement body; //тело

    public UserFunctionDefine(List<String> args, Statement body) {
        this.args = args;
        this.body = body;
    }

    public int getArgsCount() {
        return args.size();
    }

    public String getArgsName(int index) {
        if (index >= getArgsCount() || index < 0)
            return "";
        return args.get(index);
    }

    //выолянем, пока не встретим return
    @Override
    public Value execute(Value... args) {
        try {
            body.execute();
            return NumberValue.ZERO;
        } catch (ReturnStatement returnStatement) {
            return returnStatement.getResult();
        }
    }
}
