/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexexamples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jregex.Matcher;
import jregex.Pattern;
import jregex.Replacer;


/**
 *
 * @author alangonzalez
 */
public class RegexExamples {

    private static final String RULE_SEPARATOR = "->";
    private static String TEXT = "TEXT";
    private static final int RULE_NAME = 0;
    private static final int ORIGINAL_EXPRESSION = 1;
    private static final int REPLACER_EXPRESSION = 2;
   // private static final int RULE_INDEX_COUNT = 3;
    public static ArrayList<Rule> rules = new ArrayList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            readAndLoadRules();      
        } catch (IOException ex) {
            Logger.getLogger(RegexExamples.class.getName()).log(Level.SEVERE, null, ex);
        }
        String texto = "esto es texto con una palabra en *negrita* otra en #cursiva# y otra en #*negritacursiva*# y *#otra#*";
        for (int i = 0; i < rules.size(); i++) {
            Pattern pattern = new Pattern(rules.get(i).getOriginalExpression());
            Replacer replacer = pattern.replacer(rules.get(i).getReplacerExpression());
            texto = replacer.replace(texto);
        }
        System.out.println(texto);
         /*Pattern bold = new Pattern("\\*(\\p{Graph}+\\s*)\\*"); //bold
            Pattern italic = new Pattern("#(\\p{Graph}+\\s*)#"); //italic
            
            //Texto a parsear que tiene palabras en negrita, cursiva y combinadas de las dos formas
            String texto = "esto es texto con una palabra en *negr ita* otra en #cursiva# y otra en #*negritacursiva*# y *#otra#*";
            String result = "";
            
            //Primero reemplazo las negritas
            Replacer r = bold.replacer("<strong>$1</strong>");
            texto = r.replace(texto);
            //imprimo lo que resulta solo de reemplazar negritas
            System.out.println(texto);
            
            //Tomo el texto con las negritas ya reemplazadas y reemplazo las cursivas
            Replacer translatedExpr = italic.replacer("<em>$1</em>");
            result = translatedExpr.replace(texto);
            
            //Muestro resultado final
            System.out.println(result);*/
        
    }
    
    /**/
    private static void readAndLoadRules() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("rules.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                Rule newRule = textToRule(line);
                rules.add(newRule);
                System.out.println(newRule.toString());
                line = br.readLine();
            }
        } finally {
            br.close();
        }   
    }
    
    private static Rule textToRule(String line){
        String[] splittedLine = line.split(RULE_SEPARATOR);
        if(splittedLine.length != 3){ //a correct rule must be splitted in 3 parts
            System.out.println("No hay reglas cargadas o no respetan la convencion");
            return null;
        }
        String name = splittedLine[RULE_NAME].replace(" ","");
        String originalRegExp = toRegularExpression(splittedLine[ORIGINAL_EXPRESSION],false);
        String replacerRegExp = toRegularExpression(splittedLine[REPLACER_EXPRESSION],true);
        return new Rule(name,originalRegExp,replacerRegExp,rules.size()+1);
    }
    
    private static String toRegularExpression(String commonExpression, boolean isReplacer){ //example #TEXT#
        final int INITIAL_TOKEN = 0;
        final int END_TOKEN = 1;
        String result = "";
        String[] splittedExpr = commonExpression.replace(" ","").split(TEXT); // array example = {#,#}
        
        /*add escapes characters*/
        splittedExpr[INITIAL_TOKEN] = addEscapeCharacters(splittedExpr[INITIAL_TOKEN]);
        if(splittedExpr.length > 1){
            splittedExpr[END_TOKEN] = addEscapeCharacters(splittedExpr[END_TOKEN]);
        }
        
        if(!isReplacer){
            if(splittedExpr.length > 1)
                result += splittedExpr[INITIAL_TOKEN] + "(\\p{Graph}+\\s*)" + splittedExpr[END_TOKEN];
            else
                result += splittedExpr[INITIAL_TOKEN] + "(\\p{Graph}+\\s*)";
        }
        else{
            if(splittedExpr.length > 1)
                result += splittedExpr[INITIAL_TOKEN] + "$1" + splittedExpr[END_TOKEN];
            else
                result += splittedExpr[INITIAL_TOKEN] + "$1";
        }
        
        return result;
    }
    
    private static String addEscapeCharacters(String expression){
        String result = expression;
        String[] charactersToEscape = {"*","+"};
        for (String character : charactersToEscape) {
            result = result.replace(character, "\\" + character);
        }
        return result;
    }
}
