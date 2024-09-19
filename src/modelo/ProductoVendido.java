package modelo;

//Esta es DTO

public class ProductoVendido {
    
    private int idProducto;
    private float precio;
    private int cantidad;

    public ProductoVendido(int idProducto, float precio, int cantidad) {
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
