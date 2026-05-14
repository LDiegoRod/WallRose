package logica;

public class Producto {
    private static int consecutivo = 1;

    private int codigo;
    private String nombre;
    private float existencias;
    private String unidad;
    private float precio;

    public Producto(String nombre, float existencias, String unidad, float precio) {
        this.codigo = consecutivo++;
        this.nombre = nombre;
        this.existencias = existencias;
        this.unidad = unidad;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getExistencias() {
        return existencias;
    }

    public String getUnidad() {
        return unidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setExistencias(float existencias) {
        this.existencias = existencias;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}