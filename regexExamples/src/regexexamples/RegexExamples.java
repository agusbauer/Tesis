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
         
         //Texto a parsear que tiene palabras en negrita, cursiva y combinadas de las dos formas
         String texto = "esto es texto con una palabra en *negrita* otra en #cursiva# y otra en #*negritacursiva*# y *#otra#*";
         String result = "";
         
         //Primero reemplazo las negritas
         Replacer r = bold.replacer("<strong>$1</strong>");
         texto = r.replace(texto);
         //imprimo lo que resulta solo de reemplazar negritas
         System.out.println(texto);
         
         //Tomo el texto con las negritas ya reemplazadas y reemplazo las cursivas
         Replacer r2 = italic.replacer("<em>$1</em>");
         result = r2.replace(texto);
         
         //Muestro resultado final
         System.out.println(result);
    }
    
}
