/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpr;

import Abstract.AST;
import Abstract.nodoAST;
import Excepciones.Excepcion;
import TablaSimbolos.Arbol;
import TablaSimbolos.Tabla;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author josef
 */
public class JPR {
    
    private static nodoAST raiz;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        interpretar("entrada.txt");
    }
    
    private static void interpretar(String path) {
        
        ArrayList<Excepcion> Errores = new ArrayList();
        
        Analizador.Lexico scanner;
        Analizador.Sintactico parse;
        
        Arbol tree = null;
        
        try {
            scanner = new Analizador.Lexico(new BufferedReader(new FileReader(path)));
            parse = new Analizador.Sintactico(scanner);
            parse.parse(); 
            
            tree = parse.getAST();
            
            tree.setConsola("");
            Tabla tabla = new Tabla(null);
            tree.setGlobal(tabla);

            for(AST m : tree.getInstrucciones()){
                Object result = m.interpretar(tabla, tree);

                if (result instanceof Excepcion) {
                    Errores.add((Excepcion) result);
                    tree.updateConsola(((Excepcion) result).toString());
                }
            }

            nodoAST init = new nodoAST("RAIZ");
            nodoAST instr = new nodoAST("INSTRUCCIONES");
            tree.getInstrucciones().forEach(m -> {
                instr.agregarHijo(m.getNodo());
            });
            init.agregarHijo(instr);
            raiz = init;


            graphAST();
            
            System.out.println(tree.getConsola());
            
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex);
        } 
    }
    
    
    public static void graphAST()
    {
        String r = "AST";
        String ext = "pdf";
        try {
            FileWriter fw = new FileWriter("AST.dot");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println(getDot(raiz));
            fw.close();
        }
        catch (Exception rep) {
            //proyecto_diciembre.Proyecto_Diciembre.lista_errores.add(new CError("Error al escribir el dot", "Sentencia Graficar_dot ", linea, columna));
        }

        ProcessBuilder pbuilder = new ProcessBuilder("dot", "-T" + ext, "-o", r + "." + ext, r + ".dot");
        pbuilder.redirectErrorStream(true);
        try 
        {
            pbuilder.start();
        } 
        catch (IOException ex) {
            //proyecto_diciembre.Proyecto_Diciembre.lista_errores.add(new CError("Error al graficar", "Sentencia Graficar_dot ", linea, columna));
        }
    }
    
    private static int c;
    private static String grafo;
    
    public static String getDot(nodoAST raiz)
    {
        grafo = "";
        grafo += "digraph {\n";//                         "     \"
        grafo += "n0[label=\"" + raiz.getValor().replace("\"", "\\\"") + "\"];\n";
        c = 1;
        recorrerAST("n0",raiz);
        grafo += "}";
        return grafo;
    }
    
    public static void recorrerAST(String padre, nodoAST nPadre)
    {
        for(nodoAST hijo: nPadre.getHijos())
        {
            String nombreHijo = "n" + c;
            grafo += nombreHijo + "[label=\"" + hijo.getValor().replace("\"", "\\\"") + "\"];\n";
            grafo += padre + "->" + nombreHijo + ";\n";
            c++;
            recorrerAST(nombreHijo,hijo);
        }
    }
    
}
