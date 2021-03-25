import { Instruccion } from "../Abstract/Instruccion";
import Excepcion from "../Excepciones/Excepcion";
import Arbol from "../tablaSimbolos/Arbol";
import tablaSimbolos from "../tablaSimbolos/tablaSimbolos";
import Tipo, { tipos } from "../tablaSimbolos/Tipo";


export default class Imprimir extends Instruccion{
    private expresion: Instruccion;

    constructor(expresion:Instruccion, linea:Number, columna:Number){
        super(new Tipo(tipos.CADENA),linea, columna);
        this.expresion = expresion;
    }

    public interpretar(tree:Arbol, table:tablaSimbolos){
        var value = this.expresion.interpretar(tree, table); //OBTIENE EL VALOR

        if(value instanceof Excepcion) return value;

        tree.updateConsola(value+"");
    }
}