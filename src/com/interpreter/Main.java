package com.interpreter;

import com.interpreter.lexer.Lexer;
import com.interpreter.parser.Parser;
import com.interpreter.parser.ast.Expression;
import com.interpreter.parser.ast.Statement;
import com.interpreter.parser.variables.Variables;
import com.interpreter.token.Token;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
//        Пофиксил. См. метод tokenize()
        String input = "if 10 == 10 var2 = 1 else var = -1";
        String input2 = "(10+10)*2";
        Lexer lexer = new Lexer(input);
        ArrayList<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }

        Parser parser = new Parser(tokens);
        /*for (Statement statement: parser.parse()) {
            System.out.println(statement + " = " + statement.execute());
        }*/

        ArrayList<Statement> statements = parser.parse();
        for (Statement statement : statements) {
            System.out.print(statement);
            System.out.println();
        }

//        System.out.println("var1 = " + Variables.getValue("var1"));
        System.out.println("var2 = " + Variables.getValue("var2"));
//        System.out.println("check = " + Variables.getValue("check"));
    }
}
