/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresiones;

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
public class Identificador extends AST{
    private final String identificador;

    public Identificador(String identificador, int fila, int columna) {
        this.identificador = identificador;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        Object simbolo = tabla.getVariable(identificador);
        if(simbolo == null) return new Excepcion("Semantico", "Variable no encontrada", fila, columna);
        Simbolo sim = (Simbolo)simbolo;
        
        this.tipo = sim.getTipo();
        return sim.getValor();
    }
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("IDENTIFICADOR");
        nodo.agregarHijo(identificador);
        return nodo;
    }
}
