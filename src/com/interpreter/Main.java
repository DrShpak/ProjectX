package com.interpreter;

import com.interpreter.lexer.Lexer;
import com.interpreter.parser.Parser;
import com.interpreter.parser.ast.Expression;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = "(2 + 2)) * 2";
        Lexer lexer = new Lexer(input);
        /*for (Token token : lexer.tokenize()) {
            System.out.println(token);
        }*/

        Parser parser = new Parser(lexer.tokenize());
        ArrayList<Expression> expressions = parser.parse();
        for (Expression expression : expressions) {
            System.out.print(expression + " = " + expression.calculate());
        }
    }
}
