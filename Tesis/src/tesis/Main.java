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
import java.io.FileWriter;
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

public class Main {

    private static final String RULE_SEPARATOR = "->";
    private static String Text = "TEXT";
    public static ArrayList<Rule> rules = new ArrayList();
    static int negro = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //rules.add(new Rule("TEXT","","",0));
        loadRules();
        File file=new File("src/tesis/Lexer.flex");
        generate(file);
        System.out.println("\n***EJECUTANDO ANALIZADOR***\n");
        probarLexerFile();
    }
    
   public static void probarLexerFile() throws IOException{
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="";
        while (true){
            Integer token =lexer.yylex();
            if (token == null){
                System.out.println(resultado);
                return;
            }
            
            if(token > 0){
                for (int i = 0; i < rules.size(); i++) {
                    if(token == rules.get(i).getId()){
                        //System.out.println("Encontre " + rules.get(i).getName());
                        String s1 = lexer.lexeme.replace(rules.get(i).getBegin(), "");
                        if(rules.get(i).getEnd() != null){
                            s1 = s1.replace(rules.get(i).getEnd(), "");
                        }
                        //s1 = ;
                        s1 = rules.get(i).getToBegin() + s1 + rules.get(i).getToEnd();
                        System.out.print(s1);
                    }             
                }
            }
            else{
                if(token == -1){
                     System.out.print(lexer.lexeme);
                }
                else{
                    System.out.print(lexer.lexeme);
                }
            }
            /*switch (token){
                case 1:
                    System.out.println("encontre un titulo");
                    
                    break;
                case 3:
                    /*if(negro == 0){
                        negro = 1;
                        resultado = resultado+"<b>";
                    }else{
                       resultado = resultado+"</b>"; 
                       negro = 0;
                    }
                    System.out.println("encontre algo en negrita");
                    break;
                case -1:
                    resultado=resultado+ "Error, simbolo no reconocido ";
                    break;
                case 2: {
                    System.out.println("encontre solo texto");
                    break;
                }
                
                default:
                    System.out.println("entre a default");
            }*/
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
       // createFlexFile();
    }
    
    private static void ruleParser(String rule){
        String[] s = rule.split(RULE_SEPARATOR);
        if(s.length != 3){
            System.out.println("No hay reglas cargadas o no respetan la convencion");
            return;
        }
        String[] r1 = s[1].replace(" ","").split("TEXT");
        String begin = "";
        String end = null;
        begin = r1[0];
        if(r1.length == 2){          
            end = r1[1];
        }
      //  String from = s[1].replace(" ","").replace("TEXT", ""); // por ahora es asi
        String name = s[0].replace(" ","");
        
        String[] r2 = s[2].replace(" ","").split("TEXT");
        String tobegin = "";
        String toend = null;
        tobegin = r2[0];
        if(r2.length > 1){          
            toend = r2[1];
        }
       // String to = s[2].replace(" ","");
        Rule newRule = new Rule(name,begin,end,tobegin,toend,rules.size()+1);
        System.out.println(newRule.toString());
        rules.add(newRule);
    }
    
   /* private static void createFlexFile() throws IOException{
        FileWriter fw = new FileWriter("src/tesis/Lexer.flex");
        PrintWriter pw = new PrintWriter(fw);
        String text = "package tesis;\n" +
                "%%\n" +
                "%class Lexer\n" +
                "%type Integer\n" +
                "%line\n" +
                "\n" +
                "TEXT = [A-Za-z_ ][A-Za-z_0-9 ]*\n" +
                "\n" +
                "LineTerminator = \\r|\\n|\\r\\n\n" +
                "WhiteSpace     = {LineTerminator} | [ \\t\\f]\n" +
                "InputCharacter = [^\\r\\n]\n ";
        for (int i = 0; i < rules.size(); i++) {
            text += rules.get(i).getName() + " = \""+ rules.get(i).getFrom() +"\" {InputCharacter}* {LineTerminator}?\n";
        }
        text += "\n" +
                "%{\n" +
                "public String lexeme;\n" +
                "%}\n" +
                "%%\n" +
                "\n";
        for (int i = 0; i < rules.size(); i++) {
            text +=   "{"+rules.get(i).getName()+"} {lexeme = yytext(); return "+rules.get(i).getId()+";}\n";     
        }
        text +=  ". {return -1;}";
        pw.print(text);
        /*pw.print(
                + rules.get(0).getName() + " = \""+ rules.get(0).getFrom() +"\" {InputCharacter}* {LineTerminator}?\n" +
                "BOLD = \"**\" {TEXT} \"**\" {InputCharacter}* {LineTerminator}?\n" +
                "\n" +
                "%{\n" +
                "public String lexeme;\n" +
                "%}\n" +
                "%%\n" +
                "\n" +
                "{"+rules.get(0).getName()+"} {lexeme = yytext(); return "+rules.get(0).getId()+";}\n" +
                "{BOLD} {lexeme=yytext(); return 3;}\n" +
                "{TEXT} {lexeme=yytext(); return 2;}\n" +
                ". {return -1;}");
        
        pw.close();
    }*/
    
}
