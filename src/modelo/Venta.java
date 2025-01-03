/*
TO-DO: Cambiar el producto por un int, no queremos el objeto entero, solo el ID.
       Lo mismo aplica para el cliente y para el vendedor. Listo. 

*/

package modelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

//Jesus Fucking Christ, who wrote this madness?
//Nevermind, i did.
//¿Por qué tiene los métodos como sobrecargas de interfaz? Las quité.

//Tampoco es DTO, tiene más cosas que getters/setters.
public class Venta /*implements IVenta*/{
    
    private static int seqId = 0;
    
    private int idVenta;
    //Total ventas será método.
    private Date fechaVenta;
    private int idCliente;
    private int idVendedor;
    private boolean requiereFactura;
    private List <ProductoVendido> productosV  = new ArrayList<ProductoVendido>(); //Todos los prods. de la venta.
    private boolean cancelada;
    private Date fechaCancelacion;
    private String motivoCancel;

    public Venta(int idVenta, Date fechaVenta, int idCliente, int idVendedor, 
            boolean requiereFactura, List<ProductoVendido> productosV) {
    
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.requiereFactura = requiereFactura;
        this.productosV = productosV;
    
    }

    public Venta(Date fechaVenta, int idCliente, int idVendedor,
            boolean requiereFactura, List<ProductoVendido> productosV) {
    
        this.fechaVenta = fechaVenta;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.requiereFactura = requiereFactura;
    
    }

    /*
    public Venta crearVenta(int idProductoV, double cantidad, Date fechaVenta, 
                                   int idZona, int idCliente, int idVendedor, boolean requiereFactura) {

        //Lógica va aquí.
        double costoUnitario = productoV.getPrecio();
        double costoTotal = costoUnitario * cantidad;

        return new Venta(idProductoV, costoUnitario, costoTotal, fechaVenta, idZona, idCliente, idVendedor, requiereFactura);
    }
    */
    
    
    
    private static int incrementarId(){
        
        return ++seqId;
        
    } 

    
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public boolean getRequiereFactura() {
        return requiereFactura;
    }

    public void setRequiereFactura(boolean requiereFactura) {
        this.requiereFactura = requiereFactura;
    }

    public List <ProductoVendido> getProductosV() {
        return productosV;
    }

    public void setProductosV(List <ProductoVendido> productosV) {
        this.productosV = productosV;
    }

    public boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getMotivoCancel() {
        return motivoCancel;
    }

    public void setMotivoCancel(String motivoCancel) {
        this.motivoCancel = motivoCancel;
    }
    
    public double calcularTotal(){
        
        double totalVentas = 0.0;
        
        return totalVentas;
        
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idVenta;
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
        final Venta other = (Venta) obj;
        return this.idVenta == other.idVenta;
    }

    @Override
    public String toString() { //Dar formato, un dato por renglón y con \n.
        
        return
                "ID de la venta: " + idVenta + "\n"
                + "Fecha de la venta: " + fechaVenta + "\n"
                + "ID del cliente: " + idCliente + "\n"
                + "ID del vendedor: " + idVendedor + "\n"
                + "Requiere factura: " + requiereFactura + "\n"
                + "Lista de productos vendidos: " + "\n"
                + productosV + "\n";
        
    }
    
    //Subtotal es sin impuestos ni descuentos.
    public double calcularSubtotalVenta(){
        
        double subtotal = 0.0;
        
        for(int i = 0; i <= productosV.size(); i++){
            
            //Casteo explícito para evitar problemas.
            subtotal = (double)productosV.get(i).getCantidad() * productosV.get(i).getPrecio();
            
        }
        
        return subtotal;
        
    }
    
    //Cálculo del descuento de cada producto en la lista.
    public double calcularDescuento(int idProducto){
        
        double precioDescuento = 0.0;
        double factorDescuento = 0.0;        
            
        if(productosV.contains(idProducto)){
            
            if(productosV.get(idProducto).getDescuento() != 0){
                
                factorDescuento = 1 - (productosV.get(idProducto).getDescuento() / 100);
                precioDescuento = factorDescuento * productosV.get(idProducto).getPrecio();
                
            } else {
                
                throw new IllegalArgumentException("El descuento no puede ser cero.");
                
            }
            
        }

        return precioDescuento;
        
    }
    
}
