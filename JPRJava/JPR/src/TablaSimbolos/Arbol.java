package TablaSimbolos;

import Excepciones.Excepcion;
import Abstract.AST;
import java.util.ArrayList;
import javafx.scene.Group;

/**
 *
 * @author Pavel
 */
public class Arbol {

    private ArrayList<AST> instrucciones;
    private ArrayList<Excepcion> excepciones;
    private String consola;
    private Tabla global;

    public Arbol(ArrayList<AST> instrucciones) {
        this.instrucciones = instrucciones;
        this.excepciones = new ArrayList<>();
    }

    public ArrayList<AST> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(ArrayList<AST> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public ArrayList<Excepcion> getExcepciones() {
        return excepciones;
    }

    public void setExcepciones(ArrayList<Excepcion> excepciones) {
        this.excepciones = excepciones;
    }
    
    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }
    
    public void updateConsola(String consola) {
        this.consola += consola + "\n";
    }

    public Tabla getGlobal() {
        return global;
    }

    public void setGlobal(Tabla global) {
        this.global = global;
    }

      
}
