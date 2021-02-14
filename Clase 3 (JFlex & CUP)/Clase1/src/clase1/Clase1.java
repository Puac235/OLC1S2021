/*
 * Clase 3
 * Organización de Lenguajes y Compiladores 1 "A"
 * Aux. José Puac
 */
package clase1;

import java.io.FileInputStream;

/**
 *
 * @author josef
 */
public class Clase1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        interpretar("entrada.txt");
    }
    
    /**
     * Método que interpreta el contenido del archivo que se encuentra en el path
     * que recibe como parámentro
     * @param path ruta del archivo a interpretar
     */
    private static void interpretar(String path) {
        analizadores.Sintactico parse;
        try {
            parse = new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(path)));
            parse.parse();        
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
    }
    
}
