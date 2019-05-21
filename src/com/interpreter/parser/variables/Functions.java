package com.interpreter.parser.variables;

import java.util.HashMap;

public class Functions {

    private static final NumberValue ZERO = new NumberValue(0);
    private static HashMap<String, Function> functions = new HashMap<>();

    //тестовые функции
    static {
        functions.put("sin", args -> {
            if (args.length != 1)
                throw new RuntimeException("One argument expected");
            return new NumberValue(Math.sin(args[0].asDouble()));
        });

        functions.put("cout", args -> {
            for (Value arg : args) {
                System.out.print(arg.asString());
            }
            return ZERO;
        });
    }

    public static HashMap<String, Function> getFunctions() {
        return functions;
    }

    public static boolean isExists(String funcName) {
        return functions.containsKey(funcName);
    }

    public static Function get(String funcName) {
        if (!isExists(funcName))
            throw  new RuntimeException("Unknown function " + funcName);
        return functions.get(funcName);
    }

    public static void addFunction(String varName, Function function) {
        functions.put(varName, function);
    }
}