package vista;

import java.util.List;
import modelo.Producto;

/**
 *
 * @author Diego Ortega
 */
public interface IUProducto {
    
    public Producto leerProducto(int id);
    public void mostrarProducto(Producto producto);
    public int leerIdProducto(String texto);
    public String leerNombreProducto(String texto);
    public void mostrarListaProductos(List productos);
    public int menuProductos();
    public void mensaje(String cadena);
    
}
