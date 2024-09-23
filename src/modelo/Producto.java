package modelo;

import java.util.Objects;

//Para sorpresa de nadie, esta es DTO.

public class Producto {
    
    private static int seqId = 0;
    
    private int id;
    private String nombre;
    private String ubicacion;
    private double precio;
    private double costo;
    private double descuento;
    private String categoria;
    private int proveedor;
    private int stockMin;
    private int stockMax;
    private int existencias;
       
    //Constructor vacío.
    public Producto(){
        
    }
    
    // Constructor
    public Producto(String nombre, String ubicacion, double precio, double costo,
                    double descuento, String categoria, int proveedor, int stockMin,
                    int stockMax, int existencias) {
        
        this.id = incrementarId();
        
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.costo = costo;
        this.descuento = descuento;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.existencias = existencias;
        
    }

    //Setters y Getters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCosto(double costo) { this.costo = costo; }
    public void setDescuento(double descuento) { this.descuento = descuento; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setProveedor(int proveedor) { this.proveedor = proveedor; }
    public void setStockMin(int stockMin) { this.stockMin = stockMin; }
    public void setStockMax(int stockMax) { this.stockMax = stockMax; }
    public void setExistencias(int existencias) { this.existencias = existencias; }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public double getPrecio() { return precio; }
    public double getCosto() { return costo; }
    public double getDescuento() { return descuento; }
    public String getCategoria() { return categoria; }
    public int getProveedor() { return proveedor; }
    public int getStockMin() { return stockMin; }
    public int getStockMax() { return stockMax; }
    public int getExistencias() { return existencias; }    

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", precio=" + precio +
                ", costo=" + costo +
                ", descuento=" + descuento +
                ", categoria='" + categoria + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", stockMin=" + stockMin +
                ", stockMax=" + stockMax +
                ", existencias=" + existencias +
                '}';
    }
    
    //TO-DO DEFINIR EQUALS Y HASHCODE

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nombre);
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
        final Producto other = (Producto) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    
    private static int incrementarId(){
        
        return seqId++;
        
    }
    
    
}
