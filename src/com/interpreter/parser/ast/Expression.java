package com.interpreter.parser.ast;

import com.interpreter.parser.variables.Value;

public interface Expression {

    Value calculate();
}
