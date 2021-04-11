package Expresiones;

import Excepciones.Excepcion;
import Abstract.AST;
import Abstract.nodoAST;
import TablaSimbolos.Arbol;
import TablaSimbolos.Tabla;
import TablaSimbolos.Tipo;
import TablaSimbolos.Tipo.Tipos;

/**
 *
 * @author Pavel
 */
public class Aritmetica extends AST {

    public static enum OperadorAritmetico {
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        MENOSUNARIO,
        POTENCIA,
        MODULO
    }
    
    

    private AST operando1;
    private AST operando2;
    private AST operandoU;
    private final OperadorAritmetico operador;

    public Aritmetica(AST operando1, AST operando2, OperadorAritmetico operador, int fila, int columna) {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operador = operador;
        this.fila = fila;
        this.columna = columna;
    }

    public Aritmetica(AST operandoU, OperadorAritmetico operador, int fila, int columna) {
        this.operandoU = operandoU;
        this.operador = operador;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("ARITMETICA");
        if(operandoU != null)
        {
            nodo.agregarHijo(operador + "");
            nodo.agregarHijo(operandoU.getNodo());
        }
        else
        {
            nodo.agregarHijo(operando1.getNodo());
            nodo.agregarHijo(operador + "");
            nodo.agregarHijo(operando2.getNodo());
        } 
        return nodo;
    }
    
    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        Object izquierdo = null, derecho = null, unario = null;

        if (this.operandoU == null) {
            izquierdo = this.operando1.interpretar(tabla, tree);
            if (izquierdo instanceof Excepcion) return izquierdo;

            derecho = this.operando2.interpretar(tabla, tree);
            if (derecho instanceof Excepcion) return derecho;
        } else {
            unario = this.operandoU.interpretar(tabla, tree);
            if (unario instanceof Excepcion) return unario;
        }
        if (null != this.operador) switch (this.operador) {
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case SUMA:
                if (operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.ENTERO) {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return (int) izquierdo + (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (int)izquierdo + (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.BOOLEANO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return ((boolean) derecho)?(int)izquierdo + 1: (int)izquierdo + 0;//CONDICION ? VALOR TRUE : VALOR FALSE
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    int ASCII = Character.codePointAt(derecho+"", 0);//ASCII DEL CARACTER
                    return (int)izquierdo + ASCII;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo + (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo + (double)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.BOOLEANO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return ((boolean) derecho)?(double)izquierdo + 1: (double)izquierdo + 0;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double)izquierdo + c;
                }
                else if(operando1.tipo.getTipos() == Tipos.BOOLEANO
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return ((boolean) izquierdo)? 1 + (int)derecho: 0 + (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.BOOLEANO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return ((boolean) izquierdo)? 1 + (double)derecho: 0 + (double)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c + (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c + (double)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.CADENA);
                    return "" + izquierdo + derecho;
                }
                else if (operando1.tipo.getTipos() == Tipos.CADENA
                        && operando2.tipo.getTipos() == Tipos.ENTERO
                        ||
                        operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.CADENA
                        ||
                        operando1.tipo.getTipos() == Tipos.CADENA
                        && operando2.tipo.getTipos() == Tipos.DECIMAL
                        ||
                        operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.CADENA
                        ||
                        operando1.tipo.getTipos() == Tipos.CADENA
                        && operando2.tipo.getTipos() == Tipos.BOOLEANO
                        ||
                        operando1.tipo.getTipos() == Tipos.BOOLEANO
                        && operando2.tipo.getTipos() == Tipos.CADENA
                        ||
                        operando1.tipo.getTipos() == Tipos.CADENA
                        && operando2.tipo.getTipos() == Tipos.CARACTER
                        ||
                        operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.CADENA
                        ||
                        operando1.tipo.getTipos() == Tipos.CADENA
                        && operando2.tipo.getTipos() == Tipos.CADENA) {
                    this.tipo = new Tipo(Tipos.CADENA);
                    return "" + izquierdo + derecho;
                } else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador +", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case RESTA:
                if (operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.ENTERO) {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return (int) izquierdo - (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (int)izquierdo - (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.BOOLEANO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return ((boolean) derecho)?(double)izquierdo - 1: (double)izquierdo - 0;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int)izquierdo - c;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo - (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo - (double)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.BOOLEANO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return ((boolean) derecho)?(double)izquierdo - 1: (double)izquierdo - 0;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double)izquierdo - c;
                }
                else if(operando1.tipo.getTipos() == Tipos.BOOLEANO
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return ((boolean) izquierdo)? 1 - (int)derecho: 0 - (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.BOOLEANO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return ((boolean) izquierdo)? 1 - (double)derecho: 0 - (double)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c - (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c - (double)derecho;
                }
                else
                {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador -", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case MULTIPLICACION:
                if (operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.ENTERO) {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return (int) izquierdo * (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (int)izquierdo * (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int)izquierdo * c;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo * (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo * (double)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double)izquierdo * c;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c * (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c * (double)derecho;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador *", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case DIVISION:
                if (operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    if ((int) derecho == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    
                    return (int) izquierdo / (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    if ((double) derecho == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    return (int)izquierdo / (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    
                    int c = Character.codePointAt(derecho+"", 0);
                    
                    if (c == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    return ((double)(int)izquierdo / c);
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    if ((int) derecho == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    return (double) izquierdo / (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    if ((double) derecho == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    return (double) izquierdo / (double)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.CARACTER)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    
                    int c = Character.codePointAt(derecho+"", 0);
                    
                    if (c == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    return (double)izquierdo / c;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    if ((int) derecho == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    return c / (int)derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    int c = Character.codePointAt(izquierdo+"", 0);
                    if ((int) derecho == 0) {
                        return new Excepcion("Semantico", "Excepcion aritmetica, division por 0.", fila, columna);
                    }
                    return c / (double)derecho;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador /", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case MENOSUNARIO:
                if (null == operandoU.tipo.getTipos())
                {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador - Unario", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
                else switch (operandoU.tipo.getTipos()) {
                    case ENTERO:
                        this.tipo = new Tipo(Tipos.ENTERO);
                        return (int) unario * -1;
                    case DECIMAL:
                        this.tipo = new Tipo(Tipos.DECIMAL);
                        return (double) unario * -1;
                    default:
                        Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador - Unario", fila, columna);
                        tree.getExcepciones().add(ex);
                        return ex;
                }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case POTENCIA:
                if (operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.ENTERO);
                    return (int) Math.pow((int) izquierdo, (int) derecho);
                }
                else if(operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) Math.pow((int) izquierdo, (double) derecho);
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) Math.pow((double) izquierdo, (int) derecho);
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) Math.pow((double) izquierdo, (double) derecho);
                }
                else
                {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador ^", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case MODULO:
                if (operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.ENTERO) {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double)((int) izquierdo % (int) derecho);
                }
                else if (operando1.tipo.getTipos() == Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo % (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.ENTERO)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo % (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipos.DECIMAL)
                {
                    this.tipo = new Tipo(Tipos.DECIMAL);
                    return (double) izquierdo % (double) derecho;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador %", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            default:
                break;
        }
        return null;
    }
}
