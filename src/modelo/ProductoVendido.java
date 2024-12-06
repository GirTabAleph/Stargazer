package modelo;

//Esta es DTO

public class ProductoVendido{
    
    private int idProducto;
    private double precio;
    private int cantidad;
    private double descuento;    
    
    public ProductoVendido(){
        
    }
    
    public ProductoVendido(int idProducto, double precio, int cantidad, double descuento){
        
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descuento = descuento;

    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idProducto;
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
        final ProductoVendido other = (ProductoVendido) obj;
        return this.idProducto == other.idProducto;
    }

    @Override
    public String toString() {
        return "ProductoVendido{" + "idProducto=" + idProducto + ", precio=" + precio + ", cantidad=" + cantidad + '}';
    }

}
