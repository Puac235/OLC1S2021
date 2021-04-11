package Expresiones;

import Abstract.AST;
import Abstract.nodoAST;
import TablaSimbolos.Arbol;
import TablaSimbolos.Tabla;
import TablaSimbolos.Tipo;

/**
 *
 * @author Pavel
 */
public class Primitivo extends AST {
    private final Object valor;

    public Primitivo(Tipo tipo, Object valor, int fila, int columna) {
        this.valor = valor;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        return this.valor;
    }
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("PRIMITIVO");
        nodo.agregarHijo(valor+"");
        return nodo;
    }
}
