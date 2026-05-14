package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {
    private static int consecutivo = 1;

    private int numero;
    private LocalDate fecha;
    private String estado;
    private Cliente cliente;
    private List<LineaOrden> lineas;