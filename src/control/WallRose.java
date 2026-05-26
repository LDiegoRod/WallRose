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
    public void borrarProducto(int codigo) {
        Producto productoActual = obtenerProducto(codigo);
        productos.remove(productoActual);
    }

    public List<OrdenCompra> obtenerOrdenes() {
        return ordenes;
    }

    public float obtenerTotalPendiente() {
        float total = 0;

        for (OrdenCompra ordenActual : ordenes) {
            if (ordenActual.getEstado().equals("Pendiente")) {
                total += ordenActual.getMontoTotal();
            }
        }

        return total;
    }

    public int crearOrdenCompra(String idCliente) {
        Cliente clienteActual = obtenerCliente(idCliente);
        OrdenCompra ordenNueva = new OrdenCompra(clienteActual);
        ordenes.add(ordenNueva);
        return ordenNueva.getNumero();
    }

    public OrdenCompra obtenerOrdenCompra(int numero) {
        for (OrdenCompra ordenActual : ordenes) {
            if (ordenActual.getNumero() == numero) {
                return ordenActual;
            }
        }

        throw new IllegalArgumentException("No existe una orden con ese numero.");
    }

    public List<OrdenCompra> obtenerOrdenesCliente(String idCliente) {
        List<OrdenCompra> resultado = new ArrayList<>();

        for (OrdenCompra ordenActual : ordenes) {
            if (ordenActual.getCliente().getId().equals(idCliente)) {
                resultado.add(ordenActual);
            }
        }

        return resultado;
    }

    public List<OrdenCompra> obtenerOrdenesIniciadas(String idCliente) {
        List<OrdenCompra> resultado = new ArrayList<>();

        for (OrdenCompra ordenActual : ordenes) {
            if (ordenActual.getCliente().getId().equals(idCliente)
                    && ordenActual.getEstado().equals("Iniciada")) {
                resultado.add(ordenActual);
            }
        }

        return resultado;
    }

    public List<OrdenCompra> obtenerOrdenesPendientes(String idCliente) {
        List<OrdenCompra> resultado = new ArrayList<>();

        for (OrdenCompra ordenActual : ordenes) {
            if (ordenActual.getCliente().getId().equals(idCliente)
                    && ordenActual.getEstado().equals("Pendiente")) {
                resultado.add(ordenActual);
            }
        }

        return resultado;
    }

    public List<OrdenCompra> obtenerOrdenesTerminadas(String idCliente) {
        List<OrdenCompra> resultado = new ArrayList<>();

        for (OrdenCompra ordenActual : ordenes) {
            if (ordenActual.getCliente().getId().equals(idCliente)
                    && ordenActual.getEstado().equals("Terminada")) {
                resultado.add(ordenActual);
            }
        }

        return resultado;
    }

    public void agregarLinea(int numeroOrden, int codigoProducto, float cantidad) {
        OrdenCompra ordenActual = obtenerOrdenCompra(numeroOrden);
        Producto productoActual = obtenerProducto(codigoProducto);
        ordenActual.agregarLinea(productoActual, cantidad);
    }

    public void actualizarLinea(int numeroOrden, int numeroLinea, int codigoProducto, float cantidad) {
        OrdenCompra ordenActual = obtenerOrdenCompra(numeroOrden);
        Producto productoActual = obtenerProducto(codigoProducto);
        ordenActual.actualizarLinea(numeroLinea, productoActual, cantidad);
    }

    public void borrarLinea(int numeroOrden, int numeroLinea) {
        OrdenCompra ordenActual = obtenerOrdenCompra(numeroOrden);
        ordenActual.borrarLinea(numeroLinea);
    }

    public void ponerOrdenPendiente(int numero) {
        OrdenCompra ordenActual = obtenerOrdenCompra(numero);
        ordenActual.ponerPendiente();
    }

    public void ponerOrdenTerminada(int numero) {
        OrdenCompra ordenActual = obtenerOrdenCompra(numero);
        ordenActual.ponerTerminada();
    }

    public void borrarOrden(int numero) {
        OrdenCompra ordenActual = obtenerOrdenCompra(numero);
        ordenes.remove(ordenActual);
    }
    public void guardarDatos() {
        try {
  
            java.io.FileOutputStream archivo = new java.io.FileOutputStream("datos.wallrose");
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(archivo);
            

            out.writeObject(this.clientes);
            out.writeObject(this.productos);
            out.writeObject(this.ordenes);

            out.close();
            archivo.close();
            System.out.println("¡Datos respaldados con éxito en datos.wallrose!");
        } catch (java.io.IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        try {
            java.io.FileInputStream archivo = new java.io.FileInputStream("datos.wallrose");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(archivo);
            
 
            this.clientes = (java.util.ArrayList<logica.Cliente>) in.readObject();
            this.productos = (java.util.ArrayList<logica.Producto>) in.readObject();
            this.ordenes = (java.util.ArrayList<logica.OrdenCompra>) in.readObject();
            
            in.close();
            archivo.close();
            System.out.println("¡Datos cargados exitosamente desde el disco duro!");
        } catch (java.io.FileNotFoundException e) {

            System.out.println("Archivo de respaldo no encontrado. Iniciando con datos vacíos.");
        } catch (java.io.IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}