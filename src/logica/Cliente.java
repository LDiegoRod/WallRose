package logica;

import java.util.List;

public class Cliente implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String email;
	private List<OrdenCompra> ordenes;

    public Cliente(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}