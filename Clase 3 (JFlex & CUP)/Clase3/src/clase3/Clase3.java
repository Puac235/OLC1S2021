/*
 * Clase 3
 * Organización de Lenguajes y Compiladores 1 "A"
 * Aux. José Puac
 */
package clase3;

import Errores.Excepcion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
        Analizador.Lexico scanner;
        ArrayList<Excepcion> errores = new ArrayList();
        try {
            scanner = new Analizador.Lexico(new BufferedReader(new FileReader(path)));
            parse = new Analizador.Sintactico(scanner);
            parse.parse();  
            errores.addAll(scanner.Errores);
            errores.addAll(parse.getErrores());
            
            errores.forEach((err) -> {
                System.out.println(err.toString());
            });
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
    }
}
