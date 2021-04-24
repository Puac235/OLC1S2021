package Expresiones;

import Abstract.AST;
import Abstract.nodoAST;
import Excepciones.Excepcion;
import TablaSimbolos.Arbol;
import TablaSimbolos.Tabla;
import TablaSimbolos.Tipo;

/**
 *
 * @author Pavel
 */
public class Relacional extends AST {

    public static enum OperadorRelacional {
        MAYORQUE,
        MENORQUE,
        MAYORIGUAL,
        MENORIGUAL,
        IGUALACION,
        DIFERENCIACION
    }

    private final AST operando1;
    private final AST operando2;
    private final OperadorRelacional operador;

    public Relacional(AST operando1, AST operando2, OperadorRelacional operador, int fila, int columna) {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operador = operador;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public nodoAST getNodo() {
        nodoAST nodo  = new nodoAST("RELACIONAL");
        nodo.agregarHijo(operando1.getNodo());
        nodo.agregarHijo(operador + "");
        nodo.agregarHijo(operando2.getNodo());
        return nodo;
    }

    @Override
    public Object interpretar(Tabla tabla, Arbol tree) {
        Object izquierdo, derecho;

        izquierdo = this.operando1.interpretar(tabla, tree);
        if (izquierdo instanceof Excepcion) return izquierdo;

        derecho = this.operando2.interpretar(tabla, tree);
        if (derecho instanceof Excepcion) return derecho;
        
        this.tipo = new Tipo(Tipo.Tipos.BOOLEANO);
        if (null != this.operador) switch (this.operador) {
            case MENORQUE:
                if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    return (int) izquierdo < (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (int) izquierdo < (double) derecho;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER) {
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int) izquierdo < c;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO){
                    return (double) izquierdo < (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    
                    return (double) izquierdo < (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER){
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double) izquierdo < c;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c < (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c < (double) derecho;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador <", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            case MAYORQUE:
                if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {

                    return (int) izquierdo > (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    
                    return (int) izquierdo > (double) derecho;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER) {
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int) izquierdo > c;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO){
                    return (double) izquierdo > (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (double) izquierdo > (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER){
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double) izquierdo > c;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c > (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c > (double) derecho;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador >", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            case MAYORIGUAL:
                if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO){
                    return (int) izquierdo >= (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (int) izquierdo >= (double) derecho;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER) {
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int) izquierdo >= c;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO){
                    return (double) izquierdo >= (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (double) izquierdo >= (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER){
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double) izquierdo >= c;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c >= (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c >= (double) derecho;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador >=", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            case MENORIGUAL:
                if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    return (int) izquierdo <= (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (int) izquierdo <= (double) derecho;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER) {
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int) izquierdo <= c;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO){
                    return (double) izquierdo <= (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (double) izquierdo <= (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER){
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double) izquierdo <= c;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c <= (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c <= (double) derecho;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador <=", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            case IGUALACION:
                if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    return (int) izquierdo == (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (int) izquierdo == (double) derecho;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER) {
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int) izquierdo == c;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO){
                    return (double) izquierdo == (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (double) izquierdo == (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER){
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double) izquierdo == c;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c == (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c == (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.BOOLEANO
                        && operando2.tipo.getTipos() == Tipo.Tipos.BOOLEANO){
                    return (boolean) izquierdo == (boolean) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CADENA
                        && operando2.tipo.getTipos() == Tipo.Tipos.CADENA){
                    String s1 = (String) izquierdo;
                    String s2 = (String) derecho;
                    
                    int s = s1.compareTo(s2);
                    
                    return s == 0;
                }
                else {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador ==", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            case DIFERENCIACION:
                if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    return (int) izquierdo != (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (int) izquierdo != (double) derecho;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.ENTERO
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER) {
                    int c = Character.codePointAt(derecho+"", 0);
                    return (int) izquierdo != c;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO){
                    return (double) izquierdo != (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    return (double) izquierdo != (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.DECIMAL
                        && operando2.tipo.getTipos() == Tipo.Tipos.CARACTER){
                    int c = Character.codePointAt(derecho+"", 0);
                    return (double) izquierdo != c;
                }
                else if (operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.ENTERO) {
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c != (int) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CARACTER
                        && operando2.tipo.getTipos() == Tipo.Tipos.DECIMAL){
                    int c = Character.codePointAt(izquierdo+"", 0);
                    return c != (double) derecho;
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.BOOLEANO
                        && operando2.tipo.getTipos() == Tipo.Tipos.BOOLEANO){
                    return !((boolean) izquierdo != (boolean) derecho);
                }
                else if(operando1.tipo.getTipos() == Tipo.Tipos.CADENA
                        && operando2.tipo.getTipos() == Tipo.Tipos.CADENA){
                    
                    String s1 = (String) izquierdo;
                    String s2 = (String) derecho;
                    
                    int s = s1.compareTo(s2);
                    
                    return s != 0;
                }
                else
                {
                    Excepcion ex = new Excepcion("Semantico", "Error de tipos con el operador !=", fila, columna);
                    tree.getExcepciones().add(ex);
                    return ex;
                }
            default:
                break;
        }
        return null;
    }
    
}
