package TablaSimbolos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pavel
 */
public class Tabla {

    public Map<String, Simbolo> Table;
    private Tabla Anterior;

    public Tabla(Tabla Anterior) {
        this.Table = new HashMap<>();
        this.Anterior = Anterior;
    }

    public String setVariable(Simbolo simbolo) {
        for (Tabla e = this; e != null; e = e.getAnterior()) {
            Simbolo encontro = (Simbolo) (e.getTable().get(simbolo.getIdentificador()));
            if (encontro != null) {
                return "La variable con el identificador"
                        + simbolo.getIdentificador() + " ya existe.";
            }
        }
        this.Table.put(simbolo.getIdentificador(), simbolo);
        return null;
    }
    

    public Simbolo getVariable(String id) {
        for (Tabla e = this; e != null; e = e.getAnterior()) {
            Simbolo encontro = (Simbolo) (e.getTable().get(id));
            if (encontro != null) {
                return encontro;
            }
        }
        return null;
    }

    public Map<String, Simbolo> getTable() {
        return Table;
    }

    public void setTable(Map<String, Simbolo> Table) {
        this.Table = Table;
    }

    public Tabla getAnterior() {
        return Anterior;
    }

    public void setAnterior(Tabla Anterior) {
        this.Anterior = Anterior;
    }
   
}
