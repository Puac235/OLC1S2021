/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Abstract.AST;
import Abstract.nodoAST;
import TablaSimbolos.Arbol;
import TablaSimbolos.Tabla;

/**
 *
 * @author josef
 */
public class Continue extends AST {
    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        return this;
    }
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("CONTINUE");
        
        return nodo;
    }
}
