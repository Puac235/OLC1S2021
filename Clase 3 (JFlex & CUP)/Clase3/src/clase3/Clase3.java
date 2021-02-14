/*
 * Clase 3
 * Organización de Lenguajes y Compiladores 1 "A"
 * Aux. José Puac
 */
package clase3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;

/**
 *
 * @author josef
 */
public class Clase3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        interpretar("entrada.txt");
    }
    
    private static void interpretar(String path) {
        Analizador.Sintactico parse;
        try {
            parse = new Analizador.Sintactico(new Analizador.Lexico(new BufferedReader(new FileReader(path))));
            parse.parse();        
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
    }
}
