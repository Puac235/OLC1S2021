import Tipo from "../tablaSimbolos/Tipo";
import Arbol from "../tablaSimbolos/Arbol";
import TablaSimbolos from "../tablaSimbolos/tablaSimbolos";

export abstract class Instruccion {

    public tipo: Tipo;
    public linea: Number;
    public columna: Number;

    constructor(tipo:Tipo, linea : Number, columna:Number) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    abstract interpretar(arbol: Arbol, tabla: TablaSimbolos):any;
    // TODO graficar AST
}