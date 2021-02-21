/*
    Organizacion de Lenguajes y Compiladores 1 "A"
    José Puac
    Clase 4
    Método del Árbol
 */
package clase4;

import clase4.type.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author josef
 */
public class Tree {
    
    node root;

    public Tree( String er, ArrayList<node> leaves, ArrayList<ArrayList> table ) {
        
        numLeave numHoja = new numLeave(er);
        Stack pila = new Stack();
        
        String[] erSplit = er.split("");
        ArrayList<String> strList = new ArrayList<String>( Arrays.asList(erSplit));
        Collections.reverse(strList);
        
        strList.forEach((character) -> {
            switch (character) {
                case "|":
                    node lefto = (node) pila.pop();
                    node righto = (node) pila.pop();
                    
                    node no = new node(character, Types.OR, 0, lefto, righto, leaves, table);
                    pila.push(no);
                    
                    break;
                case ".":
                    node lefta = (node) pila.pop();
                    node righta = (node) pila.pop();
                    
                    node na = new node(character, Types.AND, 0, lefta, righta, leaves, table);
                    pila.push(na);
                    
                    break;
                case "*":
                    node unario = (node) pila.pop();
                    
                    node nk = new node(character, Types.KLEENE, 0, unario, null, leaves, table);
                    pila.push(nk);
                    
                    break;
                default:
                    node nd = new node(character, Types.HOJA, numHoja.getNum(), null, null, leaves, table);
                    pila.push(nd);
                    leave hoja = new leave();
                    hoja.addLeave(nd, leaves);
                    break;
            }
        });
        this.root = (node) pila.pop();
    }
    
    public node getRoot(){
        return this.root;
    }
    
}
