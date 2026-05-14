package control;

import java.util.ArrayList;
import java.util.List;

import logica.Cliente;
import logica.OrdenCompra;
import logica.Producto;

public class WallRose {
    private static WallRose instancia;

    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<OrdenCompra> ordenes;

    private WallRose() {
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.ordenes = new ArrayList<>();
    }


    public static WallRose getInstancia() {
        if (instancia == null) {
            instancia = new WallRose();
        }
        return instancia;
    }

    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    public Cliente obtenerCliente(String id) {
        for (Cliente clienteActual : clientes) {
            if (clienteActual.getId().equals(id)) {
                return clienteActual;
            }
        }

        throw new IllegalArgumentException("No existe un cliente con ese id.");
    }

    public void crearCliente(String id, String nombre, String email) {
        for (Cliente clienteActual : clientes) {
            if (clienteActual.getId().equals(id)) {
                throw new IllegalArgumentException("Ya existe un cliente con ese id.");
            }
        }

        Cliente clienteNuevo = new Cliente(id, nombre, email);
        clientes.add(clienteNuevo);
    }

    public void actualizarCliente(String id, String nombre, String email) {
        Cliente clienteActual = obtenerCliente(id);
        clienteActual.setNombre(nombre);
        clienteActual.setEmail(email);
    }

    public void borrarCliente(String id) {
        Cliente clienteActual = obtenerCliente(id);
        clientes.remove(clienteActual);
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }

    public Producto obtenerProducto(int codigo) {
        for (Producto productoActual : productos) {
            if (productoActual.getCodigo() == codigo) {
                return productoActual;
            }
        }

        throw new IllegalArgumentException("No existe un producto con ese codigo.");
    }

    public void crearProducto(String nombre, float existencias, String unidad, float precio) {
        Producto productoNuevo = new Producto(nombre, existencias, unidad, precio);
        productos.add(productoNuevo);
    }

    public void actualizarProducto(int codigo, String nombre, float existencias, String unidad, float precio) {
        Producto productoActual = obtenerProducto(codigo);
        productoActual.setNombre(nombre);
        productoActual.setExistencias(existencias);
        productoActual.setUnidad(unidad);
        productoActual.setPrecio(precio);
    }