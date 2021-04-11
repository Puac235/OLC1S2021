package Excepciones;


/**
 *
 * @author Pavel
 */
public class Excepcion {
    private final String tipo;
    private final String descripcion;
    private final int fila;
    private final int columna;

    public Excepcion(String tipo, String descripcion, int fila, int columna) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return tipo + " - " + descripcion + " [" + fila + ", " + columna + "]";
    }
    
    public String getTipo()
    {
        return tipo;
    }
    public String getDesc()
    {
        return descripcion;
    }
    public int getFila()
    {
        return fila;
    }
    public int getColumna()
    {
        return columna;
    }
}
