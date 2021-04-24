package Abstract;

import TablaSimbolos.Arbol;
import TablaSimbolos.Tabla;
import TablaSimbolos.Tipo;

/**
 *
 * @author Pavel
 */
public abstract class AST {

    public Tipo tipo;
    public int fila;
    public int columna;

    public abstract Object interpretar(Tabla tabla, Arbol tree);
    
    public abstract nodoAST getNodo();

    public Tipo getTipo() {
        return tipo;
    }
}
