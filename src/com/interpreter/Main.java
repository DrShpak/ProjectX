package com.interpreter;

import com.interpreter.lexer.Lexer;
import com.interpreter.parser.Parser;
import com.interpreter.parser.ast.Statement;
import com.interpreter.parser.variables.Variables;
import com.interpreter.token.Token;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
//        Пофиксил. См. метод tokenize()
        String input = "check = -1 Word_1 = (2 + 2) * 2\n word2 = 2 + Word_1 if Word_1 = word2 check = 1 else check = 0";
        Lexer lexer = new Lexer(input);
//        for (Token token : lexer.tokenize()) {
//            System.out.println(token);
//        }

        Parser parser = new Parser(lexer.tokenize());
        ArrayList<Statement> statements = parser.parse();
        for (Statement statement : statements) {
            System.out.print(statement);
            System.out.println();
        }

        System.out.println("Word_1 = " + Variables.getValue("Word_1"));
        System.out.println("word2 = " + Variables.getValue("word2"));
        System.out.println("check = " + Variables.getValue("check"));
    }
}
