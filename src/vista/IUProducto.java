package vista;

import modelo.Producto;

/**
 *
 * @author Diego Ortega
 */
public interface IUProducto {
    
    public Producto leerProducto();
    public void mostrarProducto();
    public int leerIdProducto();
    public String leerNombreProducto();
    public void mostrarListaProductos();
    
}
