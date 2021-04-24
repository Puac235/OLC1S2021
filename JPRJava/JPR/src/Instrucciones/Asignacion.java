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
import TablaSimbolos.Simbolo;
import TablaSimbolos.Tabla;
import TablaSimbolos.Tipo;

/**
 *
 * @author josef
 */
public class Asignacion extends AST {
    private String identificador;
    private AST valor;
    
    public Asignacion(String identificador, AST valor, int fila, int columna){
        this.identificador = identificador;
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
    }

    
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("ASIGNACION");
        nodo.agregarHijo(identificador);
        nodo.agregarHijo("=");
        nodo.agregarHijo(valor.getNodo());

        
        return nodo;
    }
    
    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        
        Object simbolo = tabla.getVariable(identificador);
        if(simbolo == null) return new Excepcion("Semantico", "Variable no encontrada", fila, columna);
        Simbolo sim = (Simbolo)simbolo;
       
        
        Object value = null;

        value = valor.interpretar(tabla, tree);
        if(value instanceof Excepcion) return value;
        
        if(!sim.getTipo().equals(valor.tipo)) return new Excepcion("Semantico", "Tipo de dato diferente en Asignacion.", fila, columna);
        
        sim.setValor(value);
        
        return null;
    }
}
