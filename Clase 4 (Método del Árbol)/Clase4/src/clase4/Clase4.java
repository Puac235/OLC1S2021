/*
    Organizacion de Lenguajes y Compiladores 1 "A"
    José Puac
    Clase 4
    Método del Árbol
 */
package clase4;

import java.util.ArrayList;

/**
 *
 * @author josef
 */
public class Clase4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
            Pasos del metodo del arbol:
            1. Aumentar la ER con el simbolo de aceptacion # y su concatenación
            2. Construir el arbol
            3. Numerar cada hoja
            4. Encontrar los anulables
            5. Tabla de primeros y ultimos
            6. Tabla de siguientes
            7. Tabla de transiciones y diagrama de estados
        */
        
        // La ER estara en prefijo
        // a . b -> . a b 
        // se aceptara el . | *
        String er = "...ab*b*|ab";
        //String er = "...*|ababb";
        
        ArrayList<node> leaves = new ArrayList();
        ArrayList<ArrayList> table = new ArrayList();
        
        er = "." + er + "#";
        
        Tree arbol = new Tree(er, leaves, table); // CREA EL ARBOL
        node raiz = arbol.getRoot();
        
        raiz.getNode(); // DETERMINA SI LOS NODOS SON ANULABLES, SUS PRIMEROS Y ULTIMOS
        raiz.follow();
        
        System.out.println("==============================TABLA SIGUIENTES==============================");
        followTable ft = new followTable();
        ft.printTable(table);
        transitionTable tran = new transitionTable(raiz, table, leaves); // bug
        System.out.println("=============================TABLA TRANSICIONES=============================");
        tran.impTable();
    }
    
}
