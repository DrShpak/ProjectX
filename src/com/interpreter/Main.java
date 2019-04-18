package com.interpreter;

import com.interpreter.lexer.Lexer;
import com.interpreter.parser.Parser;
import com.interpreter.parser.ast.Statement;
import com.interpreter.parser.variables.Variables;
import com.interpreter.token.Token;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String input = "if 36 == 36 i = 1 else i = 20";
        Lexer lexer = new Lexer(input);
        ArrayList<Token> tokens = lexer.tokenize();
        /*for (Token token : tokens) {
            System.out.println(token);
        }*/

        Parser parser = new Parser(tokens);
        ArrayList<Statement> statements = parser.parse();
        for (Statement statement : statements) {
            System.out.print(statement);
            System.out.println();
        }

        System.out.println("i = " + Variables.getValue("i"));
//        System.out.println("var2 = " + Variables.getValue("var2"));
//        System.out.println("i = " + Variables.getValue("i"));
    }
}