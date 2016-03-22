/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author agustin
 */
public class Tesis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Reglas");
        loadRules(); //deberian ser expresiones regulares?
        System.out.println("\nTexto");
        loadText(); //texto que se va a parsear de acuerdo a las reglas
    }
    
    private static void loadText() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("text.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }

    private static void loadRules() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("rules.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
}
