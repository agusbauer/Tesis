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
import java.util.ArrayList;

/**
 *
 * @author agustin
 */
public class Tesis {

    public static String RuleSeparator = "->";
    public static String Text = "text";
    public static ArrayList<String> commonSymbols = new ArrayList();
    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) throws IOException {
        System.out.println("Reglas");
        loadRules(); //deberian ser expresiones regulares?
        System.out.println("\nTexto");
        loadText(); //texto que se va a parsear de acuerdo a las reglas
    }*/
    
    private static void loadText() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("text.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                textParser(line);
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
                ruleParser(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
    
    private static void ruleParser(String rules){
        String[] s = rules.split(RuleSeparator);
        String[] ruleLeftPart = s[0].replace(" ","").split(Text);
        if(ruleLeftPart.length == 1){
            commonSymbols.add(ruleLeftPart[0]);
        }
    }
    
    private static void textParser(String txt){
        for (int i = 0; i < commonSymbols.size(); i++) {
            if(txt.contains(commonSymbols.get(i))){
                System.out.println("tiene " + commonSymbols.get(i));
            }
        }
        String[] s = txt.split(RuleSeparator);
        String[] ruleLeftPart = s[0].replace(" ","").split(Text);
        if(ruleLeftPart.length == 1){
            commonSymbols.add(ruleLeftPart[0]);
        }
    }
}
