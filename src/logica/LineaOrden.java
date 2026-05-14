package logica;

public class LineaOrden {
    private Producto producto;
    private float cantidad;

    public LineaOrden(Producto producto, float cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getCosto() {
        return cantidad * producto.getPrecio();
    }
}