import { Instruccion } from "../Abstract/Instruccion";
import Excepcion from "../Excepciones/Excepcion";
import Arbol from "../tablaSimbolos/Arbol";
import tablaSimbolos from "../tablaSimbolos/tablaSimbolos";
import Tipo, { tipos } from "../tablaSimbolos/Tipo";

export default class Aritmetica extends Instruccion {
    
    private op1:Instruccion|undefined;
    private op2:Instruccion|undefined;
    private opU:Instruccion|undefined;
    private operador:OperadorAritmetico;

    constructor(operador:OperadorAritmetico, fila:Number, columna:Number, operando1:Instruccion, operando2?:Instruccion){
        super(new Tipo(tipos.ENTERO), fila, columna);
        this.operador = operador;
        if(!operando2){
            this.opU = operando1;
        }
        else{
            this.op1 = operando1;
            this.op2 = operando2;
        }
    }

    public interpretar(tree:Arbol, table:tablaSimbolos){
        var izquierdo = null, derecho = null, unario = null;
        
        if(this.opU == (null||undefined)){
            izquierdo = this.op1?.interpretar(tree,table);
            if(izquierdo instanceof Excepcion) return izquierdo;

            derecho = this.op2?.interpretar(tree,table);
            if(derecho instanceof Excepcion) return derecho;
        }
        else{
            unario = this.opU.interpretar(tree,table);
            if(unario instanceof Excepcion) return unario;
        }

        if(this.operador == OperadorAritmetico.SUMA){
            if(this.op1?.tipo.getTipos() == tipos.ENTERO){
                if(this.op2?.tipo.getTipos() == tipos.ENTERO){
                    this.tipo = new Tipo(tipos.ENTERO);
                    return parseInt(izquierdo) + parseInt(derecho);
                }
                else if(this.op2?.tipo.getTipos() == tipos.DECIMAL){
                    this.tipo = new Tipo(tipos.DECIMAL);
                    return parseInt(izquierdo) + parseFloat(derecho);
                }
                else if(this.op2?.tipo.getTipos() == tipos.CADENA){
                    this.tipo = new Tipo(tipos.CADENA);
                    return izquierdo + "" + derecho;
                }
                // BOOLEAN
                // CHAR
            }
            else{
                return new Excepcion("Semántico", "Operandos erroneos para +", this.linea, this.columna);
            }
        }else{
            return new Excepcion("Semántico","Tipo de Operación Erróneo.",this.linea,this.columna);
        }

    }

}

export enum OperadorAritmetico{
    SUMA,
    RESTA,
    MULTIPLICACION,
    DIVISION,
    MENOSUNARIO,
    POTENCIA,
    MODULO
}
