package com.interpreter.parser.variables;

import java.util.HashMap;

public class Variables {

    private static HashMap<String, Double> variables = new HashMap<>();

    public static double getValue(String varName) {
        return variables.get(varName);
    }

    public static void addVariable(String varName, double value) {
        variables.put(varName, value);
    }
}
