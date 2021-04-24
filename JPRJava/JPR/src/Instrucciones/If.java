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
public class If extends AST {
    private AST condicion;
    private ArrayList<AST> instruccionesIf;
    private ArrayList<AST> instruccionesElse;
    private AST elseIf;

    public If(AST condicion, ArrayList<AST> instruccionesIf, int fila, int columna) {
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.fila = fila;
        this.columna = columna;
    }

    public If(AST condicion, ArrayList<AST> instruccionesIf, ArrayList<AST> instruccionesElse, int fila, int columna) {
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionesElse = instruccionesElse;
        this.fila = fila;
        this.columna = columna;
    }

    public If(AST condicion, ArrayList<AST> instruccionesIf, AST elseIf, int fila, int columna) {
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.elseIf = elseIf;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        Object value = condicion.interpretar(tabla, tree);
        if(value instanceof Excepcion) return value;
        
        if(!(value instanceof Boolean)){
            Excepcion ex = new Excepcion("Semantico", "Se esperaba un valor booleano para la condicion", fila, columna);
            return ex;
        }
        Object result = null;
        if((Boolean) value){
            Tabla nuevaTabla = new Tabla(tabla);
            for(AST m : instruccionesIf){
                result = m.interpretar(nuevaTabla, tree);
                if(result instanceof Excepcion){
                    Excepcion ex = new Excepcion("Semantico", "Se esperaba un valor booleano para la condicion", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
                if(result instanceof Detener)return result;
                if(result instanceof Continue)return result;
                
            }
        }else{
            if(instruccionesElse != null){ // ELSE
                Tabla nuevaTabla = new Tabla(tabla);
                for(AST m : instruccionesElse){
                    result = m.interpretar(nuevaTabla, tree);
                    if(result instanceof Excepcion){
                        Excepcion ex = new Excepcion("Semantico", "Se esperaba un valor booleano para la condicion", fila, columna);
                        tree.getExcepciones().add(ex);
                        return ex;
                    }
                    if(result instanceof Detener)return result;
                    if(result instanceof Continue)return result;

                }
            }else if(elseIf != null){ // ELSE IF
                result = elseIf.interpretar(tabla, tree);
                if(result instanceof Excepcion)return result;
                if(result instanceof Detener)return result;
                if(result instanceof Continue)return result;
            }
        }
        
        return null;
    }
    
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("IF");
        nodo.agregarHijo("if");
        nodo.agregarHijo("(");
        nodo.agregarHijo(condicion.getNodo());
        nodo.agregarHijo(")");
        nodo.agregarHijo("{");
        nodoAST nodo2  = new nodoAST("INSTRUCCIONES IF");
        for(AST ins: instruccionesIf){
            nodo2.agregarHijo(ins.getNodo());
        }
        nodo.agregarHijo(nodo2);
        nodo.agregarHijo("}");
        if(instruccionesElse != null){ // ELSE
            nodo.agregarHijo("else");
            nodo.agregarHijo("{");
            nodoAST nodo3  = new nodoAST("INSTRUCCIONES ELSE");
            for(AST ins: instruccionesElse){
                nodo2.agregarHijo(ins.getNodo());
            }
            nodo.agregarHijo(nodo3);
            nodo.agregarHijo("}");
        }else if(elseIf != null){ // ELSE IF
            nodo.agregarHijo("else");
            nodo.agregarHijo(elseIf.getNodo());
        }
        return nodo;
    }
    
    
}
