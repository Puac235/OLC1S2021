/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import java.util.LinkedList;

/**
 *
 * @author josef
 */
public class nodoAST {
    LinkedList<nodoAST> hijos;
    String valor;   

    public nodoAST(String valor) {
        this.hijos= new LinkedList();
        this.valor=valor;    
    }

    
    public void setHijos(LinkedList<nodoAST> hijos){
        this.hijos = hijos;
    }
    
    public void agregarHijo(String cad){
        hijos.add(new nodoAST(cad));
    }
    
    public void agregarHijos(LinkedList<nodoAST> hijos){
        for(nodoAST hijo : hijos)
        {
            this.hijos.add(hijo);
        }
    }
    
    public void agregarHijo(nodoAST hijo)
    {
        hijos.add(hijo);
    }
    
    public void agregarPrimerHijo(String cad)
    {
        hijos.addFirst(new nodoAST(cad));
    }
    
    public void agregarPrimerHijo(nodoAST hijo)
    {
        hijos.addFirst(hijo);
    }
    
    public String getValor()
    {
        return valor;
    }
    
    public void setValor(String cad)
    {
        valor = cad;
    }
    
    public LinkedList<nodoAST> getHijos()
    {
        return hijos;
    }
}
