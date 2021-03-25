import { Instruccion } from "../Abstract/Instruccion";
import Arbol from "../tablaSimbolos/Arbol";
import tablaSimbolos from "../tablaSimbolos/tablaSimbolos";
import Tipo from "../tablaSimbolos/Tipo";


export default class Primitivo extends Instruccion{
    private valor: any;

    constructor(tipo:Tipo, valor:any, linea:Number, columna:Number){
        super(tipo, linea, columna);
        this.valor = valor;
    }

    public interpretar(tree:Arbol, table:tablaSimbolos){
        return this.valor;
    }
}