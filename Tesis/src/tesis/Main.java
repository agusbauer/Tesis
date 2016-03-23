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

     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //File file=new File("/Users/alangonzalez/Desktop/tesis/Tesis/Tesis/src/tesis/Lexer.flex");
        //generate(file);
        probarLexerFile();
    }
    
    public static void probarLexerFile() throws IOException{
        File fichero = new File ("fichero.txt");
        List<identificador> tokenslist = new LinkedList<identificador>();
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print("###esto es un titulo mediano");
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("error");
        }
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="";
        while (true){
            Token token =lexer.yylex();
            if (token == null){
                for(int i=0;i<tokenslist.size();i++){
                    System.out.println(tokenslist.get(i).nombre);
                }
                System.out.println(resultado);
                return;
            }
            switch (token){
                case TITLE:
                    System.out.println(lexer.lexeme);
                    resultado=resultado+ "<h2>";
                    break;
                
                case ERROR:
                    resultado=resultado+ "Error, simbolo no reconocido ";
                    break;
                case ID: {
                    identificador tokenitem=new identificador();
                    tokenitem.nombre=lexer.lexeme;
                    resultado = resultado+lexer.lexeme;
                    tokenslist.add(tokenitem);
                    
                    
                    break;
                }
                
                default:
                    resultado=resultado+ "<"+ lexer.lexeme + "> ";
            }
    }
 }
    
}
