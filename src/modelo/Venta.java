/*
TO-DO: Cambiar el producto por un int, no queremos el objeto entero, solo el ID.
       Lo mismo aplica para el cliente y para el vendedor. Listo. 

*/

package modelo;

import java.util.Date;

//Jesus Fucking Christ, who wrote this madness?
//Nevermind, i did.
//¿Por qué tiene los métodos como sobrecargas de interfaz? Para este punto ya me
// vale madres. -XOXO Diego.

//Tampoco es DTO, tiene más cosas que getters/setters.
public class Venta /*implements IVenta*/{
    
    private static int seqId = 0;
    
    private int idVenta;
    private ProductoVendido productoV; //¿?
    private int idProductoV;
    private double costoUnitario; 
    private double costoTotal;
    private Date fechaVenta;
    private int idZona;
    private int idCliente;
    private int idVendedor;
    private boolean requiereFactura;

    public Venta(int idProductoV, double costoUnitario, double costoTotal, 
            Date fechaVenta, int idZona, int idCliente, int idVendedor,
            boolean requiereFactura) {
        
        this.idProductoV = idProductoV;
        this.costoUnitario = costoUnitario;
        this.costoTotal = costoTotal;
        this.fechaVenta = fechaVenta;
        this.idZona = idZona;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.requiereFactura = requiereFactura;
   
    }

    public Venta(int idVenta, ProductoVendido productoV, int idProductoV, double costoUnitario, double costoTotal, Date fechaVenta, int idZona, int idCliente, int idVendedor, boolean requiereFactura) {
        
        if(idVenta == 0){
            
            this.idVenta = idVenta;
            
        } else {
            
            this.idVenta = incrementarId();
            
        }
        
        this.idVenta = idVenta;
        this.productoV = productoV;
        this.idProductoV = idProductoV;
        this.costoUnitario = costoUnitario;
        this.costoTotal = costoTotal;
        this.fechaVenta = fechaVenta;
        this.idZona = idZona;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.requiereFactura = requiereFactura;
   
    }
    
    public Venta crearVenta(int idProductoV, double cantidad, Date fechaVenta, 
                                   int idZona, int idCliente, int idVendedor, boolean requiereFactura) {

        //Lógica va aquí.
        double costoUnitario = productoV.getPrecio();
        double costoTotal = costoUnitario * cantidad;

        return new Venta(idProductoV, costoUnitario, costoTotal, fechaVenta, idZona, idCliente, idVendedor, requiereFactura);
    }
    
    public boolean requiereFactura() {
        return requiereFactura;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public Date getFechaVenta() {
        return fechaVenta;
    }
    
    public ProductoVendido getProductoVendido() {
        return productoV;
    }
    
    public double getCostoTotal() {
        return costoTotal;
    }

    public ProductoVendido getProductoV() {
        return productoV;
    }

    public int getIdProductoV() {
        return idProductoV;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public int getIdZona() {
        return idZona;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public boolean getRequiereFactura() {
        return requiereFactura;
    }
    
    private static int incrementarId(){
        
        return ++seqId;
        
    } 
    
}
