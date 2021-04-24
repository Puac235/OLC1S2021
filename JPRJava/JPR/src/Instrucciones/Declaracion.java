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
import TablaSimbolos.Tipo.Tipos;

/**
 *
 * @author josef
 */
public class Declaracion extends AST {
    private String identificador;
    private AST valor;
    
    public Declaracion(Tipo tipo,String identificador, AST valor, int fila, int columna){
        this.identificador = identificador;
        this.tipo = tipo;
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
    }
    
    public Declaracion(Tipo tipo,String identificador, int fila, int columna){
        this.identificador = identificador;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("DECLARACION");
        nodo.agregarHijo(tipo.getTipos()+"");
        nodo.agregarHijo(identificador);
        
        if(valor != null)
        {
            nodo.agregarHijo("=");
            nodo.agregarHijo(valor.getNodo());
        }
        
        return nodo;
    }
    
    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        Object value = null;
        if(valor != null){
            value = valor.interpretar(tabla, tree);
            if(value instanceof Excepcion) return value;
            if(!tipo.equals(valor.tipo)) return new Excepcion("Semantico", "Tipo de dato diferente en Declaracion.", fila, columna);
        }else{
            if(null != tipo.getTipos())switch (tipo.getTipos()) {
                case BOOLEANO:
                    value = true;
                    break;
                case CADENA:
                    value = "";
                    break;
                case CARACTER:
                    value = '\u0000';
                    break;
                case DECIMAL:
                    value = 0.0;
                    break;
                case ENTERO:
                    value = 0;
                    break;
                default:
                    break;
            }
        }
        
        Simbolo sim;
        
        sim = new Simbolo(tipo, identificador, value);
        
        Object insercion = tabla.setVariable(sim);
        if(insercion != null) return new Excepcion("Semantico", insercion + "", fila, columna);
        return null;
    }
}
