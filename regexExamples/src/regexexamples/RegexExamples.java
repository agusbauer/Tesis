/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexexamples;

import jregex.Matcher;
import jregex.Pattern;
import jregex.Replacer;


/**
 *
 * @author alangonzalez
 */
public class RegexExamples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Pattern bold = new Pattern("\\*(\\p{Graph}+\\s*)\\*"); //bold
         Pattern italic = new Pattern("#(\\p{Graph}+\\s*)#"); //italic
         
         String texto = "esto es texto con una palabra en *negrita* otra en #cursiva# y otra en #*negritacursiva*# y *#otra#*";
         String result = "";
         
         Replacer r = bold.replacer("<strong>$1</strong>");
         texto = r.replace(texto);
         
         System.out.println(texto);
         
         Replacer r2 = italic.replacer("<em>$1</em>");
         result = r2.replace(texto);
         
         System.out.println(result);
         
         
         
         /*Matcher m=p.matcher(texto);
         while(m.find()){
            System.out.println("next word: ["+m.toString()+"]");
        }*/
         
       
    }
    
}
