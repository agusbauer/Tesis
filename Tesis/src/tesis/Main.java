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

    static int negro = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file=new File("/Users/alangonzalez/Desktop/tesis/Tesis/Tesis/src/tesis/Lexer.flex");
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
                case NEGRITA:
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
                case TEXTO: {
                    System.out.println("encontre solo texto");
                    break;
                }
                
                default:
                    System.out.println("entre a default");
            }
    }
 }
    
}
