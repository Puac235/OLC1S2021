/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Abstract.AST;
import Abstract.nodoAST;
import Excepciones.Excepcion;
import TablaSimbolos.Arbol;
import TablaSimbolos.Tabla;
import java.util.ArrayList;

/**
 *
 * @author josef
 */
public class HacerMientras extends AST {
    private AST condicion;
    private ArrayList<AST> instrucciones;
    
    public HacerMientras(ArrayList<AST> instrucciones, AST condicion, int fila, int columna) {
        this.condicion = condicion;
        this.instrucciones = instrucciones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        do{
            Object result = null;
            Tabla nuevaTabla = new Tabla(tabla);
            for(AST m : instrucciones){
                result = m.interpretar(nuevaTabla, tree);
                if(result instanceof Excepcion){
                    Excepcion ex = new Excepcion("Semantico", "Se esperaba un valor booleano para la condicion", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
                if(result instanceof Detener)return null;
                if(result instanceof Continue)break;

            }
            Object value = condicion.interpretar(tabla, tree);
            if(value instanceof Excepcion) return value;

            if(!(value instanceof Boolean)){
                Excepcion ex = new Excepcion("Semantico", "Se esperaba un valor booleano para la condicion", fila, columna);
                return ex;
            }  
            if(!(Boolean) value)break;

        }while(true);
        
        
        return null;
    }
    
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("DO WHILE");
        nodo.agregarHijo("do");
        nodo.agregarHijo("{");
        nodoAST nodo2  = new nodoAST("INSTRUCCIONES");
        for(AST ins: instrucciones){
            nodo2.agregarHijo(ins.getNodo());
        }
        nodo.agregarHijo(nodo2);
        nodo.agregarHijo("}");
        nodo.agregarHijo("while");
        nodo.agregarHijo("(");
        nodo.agregarHijo(condicion.getNodo());
        nodo.agregarHijo(")");
        nodo.agregarHijo(";");
        
        
        return nodo;
    }
}
