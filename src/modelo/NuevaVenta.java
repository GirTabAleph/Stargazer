package modelo;

import javax.swing.JOptionPane;
import java.util.Date;

public class NuevaVenta {
    
    private int idVenta;
    private IDAOProductoVendido productos = new DAOProductoVendidoArrayList();
    private double precioTotal;
    private Date fechaVenta;
    private String zona;
    private Cliente cliente;
    private Vendedor vendedor;
    private boolean requiereFactura;

    public NuevaVenta(/*int idVenta,*/ double precioTotal, Date fechaVenta, String zona, 
            Cliente cliente, Vendedor vendedor, boolean requiereFactura, 
            IDAOProductoVendido productos) {
        //this.idVenta = idVenta;
        this.precioTotal = precioTotal;
        this.fechaVenta = fechaVenta;
        this.zona = zona;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.requiereFactura = requiereFactura;
        this.productos = productos;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public IDAOProductoVendido getProductos() {
        return productos;
    }

    public void setProductos(IDAOProductoVendido productos) {
        this.productos = productos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public boolean isRequiereFactura() {
        return requiereFactura;
    }

    public void setRequiereFactura(boolean requiereFactura) {
        this.requiereFactura = requiereFactura;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idVenta;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NuevaVenta other = (NuevaVenta) obj;
        return this.idVenta == other.idVenta;
    }

    @Override
    public String toString() {
        return "NuevaVenta{" + "idVenta=" + idVenta + ", productos=" + productos + ", precioTotal=" + precioTotal + ", fechaVenta=" + fechaVenta + ", zona=" + zona + ", cliente=" + cliente + ", vendedor=" + vendedor + ", requiereFactura=" + requiereFactura + '}';
    }

    
    
}