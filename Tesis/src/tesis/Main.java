/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jflex.*;
import static jflex.Main.generate;

/**
 *
 * @author alangonzalez
 */
public class Main {

    private static String RuleSeparator = "->";
    private static String Text = "text";
    public static ArrayList<Rule> rules = new ArrayList();
    static int negro = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        loadRules();
        File file=new File("Lexer.flex");
        generate(file);
        System.out.println("\n***EJECUTANDO ANALIZADOR***\n");
        probarLexerFile();
    }
    
    public static void probarLexerFile() throws IOException{
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="";
        while (true){
            Token token =lexer.yylex();
            if (token == null){
                System.out.println(resultado);
                return;
            }
            switch (token){
                case TITLE:
                    System.out.println("encontre un titulo");
                    
                    break;
                case BOLD:
                    /*if(negro == 0){
                        negro = 1;
                        resultado = resultado+"<b>";
                    }else{
                       resultado = resultado+"</b>"; 
                       negro = 0;
                    }*/
                    System.out.println("encontre algo en negrita");
                    break;
                case ERROR:
                    resultado=resultado+ "Error, simbolo no reconocido ";
                    break;
                case TEXT: {
                    System.out.println("encontre solo texto");
                    break;
                }
                
                default:
                    System.out.println("entre a default");
            }
        }
    }
    
    private static void loadRules() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("rules.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                ruleParser(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
    
    private static void ruleParser(String rule){
        String[] s = rule.split(RuleSeparator);
        if(s.length != 3){
            System.out.println("No hay reglas cargadas o no respetan la convencion");
            return;
        }
        String from = s[1].replace(" ","");
        String name = s[0].replace(" ","");
        String to = s[2].replace(" ","");
        Rule newRule = new Rule(name,from,to);
        System.out.println(newRule.toString());
        rules.add(newRule);
    }
    
}
