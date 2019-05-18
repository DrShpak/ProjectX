package com.interpreter.parser.variables;

import java.util.HashMap;

public class Variables {

    private static final NumberValue ZERO = new NumberValue(0);
    private static HashMap<String, Value> variables = new HashMap<>();

    public static HashMap<String, Value> getVariables() {
        return variables;
    }

    public static boolean isExists(String varName) {
        return variables.containsKey(varName);
    }

    public static Value getValue(String varName) {
        if (!isExists(varName))
            return ZERO;
        return variables.get(varName);
    }

    public static void addVariable(String varName, Value value) {
        variables.put(varName, value);
    }
}