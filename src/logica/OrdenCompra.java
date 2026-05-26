package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private static int consecutivo = 1;

	private int numero;
	private LocalDate fecha;
	private String estado;
	private Cliente cliente;
	private List<LineaOrden> lineas;

    public OrdenCompra(Cliente cliente) {
        this.numero = consecutivo++;
        this.fecha = LocalDate.now();
        this.estado = "Iniciada";
        this.cliente = cliente;
        this.lineas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<LineaOrden> obtenerLineas() {
        return lineas;
    }

    public void agregarLinea(Producto producto, float cantidad) {
        LineaOrden nuevaLinea = new LineaOrden(producto, cantidad);
        lineas.add(nuevaLinea);
    }

    public void actualizarLinea(int numeroLinea, Producto producto, float cantidad) {
        if (numeroLinea < 0 || numeroLinea >= lineas.size()) {
            throw new IllegalArgumentException("El numero de linea no es valido.");
        }

        LineaOrden linea = lineas.get(numeroLinea);
        linea.setProducto(producto);
        linea.setCantidad(cantidad);
    }

    public void borrarLinea(int numeroLinea) {
        if (numeroLinea < 0 || numeroLinea >= lineas.size()) {
            throw new IllegalArgumentException("El numero de linea no es valido.");
        }

        lineas.remove(numeroLinea);
    }

    public void ponerPendiente() {
        this.estado = "Pendiente";
    }

    public void ponerTerminada() {
        this.estado = "Terminada";
    }

    public float getMonto() {
        float monto = 0;

        for (LineaOrden linea : lineas) {
            monto += linea.getCosto();
        }

        return monto;
    }

    public float getImpuesto() {
        return getMonto() * 0.13f;
    }

    public float getMontoTotal() {
        return getMonto() + getImpuesto();
    }
}