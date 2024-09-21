package vista;

import modelo.Producto;

/**
 *
 * @author Diego Ortega
 */
public interface IUProducto {
    
    public Producto leerProducto();
    public void mostrarProducto(Producto producto);
    public int leerIdProducto(String texto);
    public String leerNombreProducto(String texto);
    public void mostrarListaProductos(Producto[] productos);
    public int menuProductos();
    public void mensaje(String cadena);
    
}
