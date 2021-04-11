package TablaSimbolos;

/**
 *
 * @author Pavel
 */
public class Tipo {

    public static enum Tipos {
        ENTERO,
        DECIMAL,
        CADENA,
        BOOLEANO,
        CARACTER,
    };

    private Tipos tipos;
    private String tipoObjeto;

    public Tipo(Tipos tipos) {
        this.tipos = tipos;
    }

    public Tipo(Tipos tipos, String tipoObjeto) {
        this.tipos = tipos;
        this.tipoObjeto = tipoObjeto;
    }

    @Override
    public String toString() {
        if (tipoObjeto == null) {
            return this.tipos + "";
        }
        return this.tipos + ":" + this.tipoObjeto;
    }

    public boolean equals(Tipo obj) {
        if (this.tipoObjeto == null && obj.tipoObjeto == null) {
            return this.tipos == obj.tipos;
        } else if (this.tipoObjeto != null && obj.tipoObjeto != null) {
            return this.tipoObjeto.equals(obj.tipoObjeto);
        }
        return false;
    }

    public Tipos getTipos() {
        return tipos;
    }

    public void setTipos(Tipos tipos) {
        this.tipos = tipos;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }
}
