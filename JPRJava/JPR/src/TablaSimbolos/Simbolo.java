package TablaSimbolos;

/**
 *
 * @author Pavel
 */
public class Simbolo {

    private Tipo tipo;
    private String identificador;
    private Object valor;


    public Simbolo(Tipo tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }

    public Simbolo(Tipo tipo, String identificador, Object valor) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.valor = valor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
