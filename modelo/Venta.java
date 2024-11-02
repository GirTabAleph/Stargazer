package modelo;

import java.time.LocalDateTime;

//Tampoco es DTO, tiene más cosas que getters/setters.

public class Venta {
    private Producto producto;
    private double costoUnitario;
    private double costoTotal;
    private LocalDateTime fechaVenta;
    private String zona;
    private Cliente cliente;
    private Vendedor vendedor;
    private boolean requiereFactura;

    public Venta(Producto producto, double costoUnitario, double costoTotal, LocalDateTime fechaVenta, 
                 String zona, Cliente cliente, Vendedor vendedor, boolean requiereFactura) {
        this.producto = producto;
        this.costoUnitario = costoUnitario;
        this.costoTotal = costoTotal;
        this.fechaVenta = fechaVenta;
        this.zona = zona;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.requiereFactura = requiereFactura;
    }

    // Método para crear una venta
    public static Venta crearVenta(Producto producto, double cantidad, LocalDateTime fechaVenta, 
                                   String zona, Cliente cliente, Vendedor vendedor, boolean requiereFactura) {

        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (vendedor == null) {
            throw new IllegalArgumentException("El vendedor no puede ser nulo.");
        }

        double costoUnitario = producto.getPrecio();
        double costoTotal = costoUnitario * cantidad;

        return new Venta(producto, costoUnitario, costoTotal, fechaVenta, zona, cliente, vendedor, requiereFactura);
    }

    public boolean requiereFactura() {
        return requiereFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public double getCostoTotal() {
        return costoTotal;
    }
}
