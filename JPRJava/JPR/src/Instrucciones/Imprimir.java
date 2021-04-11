package Instrucciones;

import Abstract.AST;
import Abstract.nodoAST;
import Excepciones.Excepcion;
import TablaSimbolos.Arbol;
import TablaSimbolos.Simbolo;
import TablaSimbolos.Tabla;
import TablaSimbolos.Tipo;
import TablaSimbolos.Tipo.Tipos;
import java.util.ArrayList;
import javafx.scene.control.TextArea;

/**
 *
 * @author Pavel
 */
public class Imprimir extends AST {

    private AST expresion;

    public Imprimir(AST expresion, int fila, int columna) {
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        Object value = expresion.interpretar(tabla, tree);
        if (value instanceof Excepcion) return value;
        
        tree.updateConsola(value + "");
        
        return null;
    }
    
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("PRINT");
        nodo.agregarHijo("print");
        nodo.agregarHijo("(");
        nodo.agregarHijo(expresion.getNodo());
        nodo.agregarHijo(")");
        return nodo;
    }
}
