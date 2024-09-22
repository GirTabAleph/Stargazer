package modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Factura {
    private String rfcCliente;
    private String nombreCliente;
    private String domicilioCliente;
    private String telefonoCliente;
    private int numeroFactura;
    private LocalDate fechaEmitida;
    private List<Producto> productos;
    private double impuestoTotal;
    private double costoTotal;

    public Factura(Cliente cliente, int numeroFactura, LocalDate fechaEmitida, List<Producto> productos) {
        if (cliente == null || productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("Cliente y productos no pueden ser nulos o vacíos.");
        }
        this.rfcCliente = cliente.getRfc();
        this.nombreCliente = cliente.getNombre();
        this.domicilioCliente = cliente.getDomicilio();
        this.telefonoCliente = cliente.getTelefono();
        this.numeroFactura = numeroFactura;
        this.fechaEmitida = fechaEmitida;
        this.productos = productos;
        this.impuestoTotal = calcularImpuestoTotal();
        this.costoTotal = calcularCostoTotal();
        
    }

    private double calcularImpuestoTotal() {
        double totalImpuesto = 0;
        for (Producto producto : productos) {
            totalImpuesto += producto.getPrecio() * 0.16; // 16% de IVA
        }
        return totalImpuesto;
    }

    private double calcularCostoTotal() {
        double total = 0;
        for (Producto producto : productos) {
            double precioConDescuento = producto.getPrecio() - producto.getDescuento();
            total += precioConDescuento * producto.getCantidad();
        }
        return total + impuestoTotal;
    }

    public String generarFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append(String.format("Factura N°: %d\n", numeroFactura));
        factura.append(String.format("Fecha emitida: %s\n", fechaEmitida));
        factura.append(String.format("Cliente: %s\n", nombreCliente));
        factura.append(String.format("RFC: %s\n", rfcCliente));
        factura.append(String.format("Domicilio: %s\n", domicilioCliente));
        factura.append(String.format("Teléfono: %s\n", telefonoCliente));
        factura.append("\nProductos:\n");

        for (Producto producto : productos) {
            factura.append(String.format("- %s: $%.2f (Descuento: $%.2f)\n",
                    producto.getNombre(), producto.getPrecio(), producto.getDescuento()));
        }

        factura.append(String.format("\nImpuesto Total: $%.2f\n", impuestoTotal));
        factura.append(String.format("Costo Total: $%.2f\n", costoTotal));

        return factura.toString();
    }
    
    public int getNumeroFactura() {
        
        return numeroFactura;
    
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Factura factura = (Factura) obj;
        return numeroFactura == factura.numeroFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroFactura);
    }
}
