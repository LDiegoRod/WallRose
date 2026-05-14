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

