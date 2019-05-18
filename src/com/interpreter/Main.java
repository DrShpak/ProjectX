package com.interpreter;

import com.interpreter.lexer.Lexer;
import com.interpreter.parser.Parser;
import com.interpreter.parser.ast.Statement;
import com.interpreter.parser.variables.Value;
import com.interpreter.parser.variables.Variables;
import com.interpreter.token.Token;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

//        String input1 = "word = \"lol\" word2 = \"kekos\" if word < word2 print \"wow\" else print \"not wow\" ";
//        String input = new String((Files.readAllBytes(Paths.get("src/com/interpreter/test/input.txt")), StandardCharsets.UTF_8);
        StringBuilder input = new StringBuilder();
//        Files.readAllLines(Paths.get("/Users/test/Documents/Java programmes/ProjectX/src/com/interpreter/test/input.txt"))
//                .forEach(str -> input.append(str).append(" "));
        final String string = new String (Files.readAllBytes(Paths.get("/Users/test/Documents/Java programmes/ProjectX/src/com/interpreter/test/input.txt")), "UTF-8");
        Lexer lexer = new Lexer(string);
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

        for (Statement statement : statements) {
            statement.execute();
        }

        Set<Map.Entry<String, Value>> set = Variables.getVariables().entrySet();
        for (Map.Entry<String, Value> entry : Variables.getVariables().entrySet()) {
//            System.out.println(Variables.getVariables().get(entry.getKey()));
        }

        System.out.println("\na = " + Variables.getValue("a"));
//        System.out.println("var2 = " + Variables.getValue("var2"));
//        System.out.println("i = " + Variables.getValue("i"));
    }
}