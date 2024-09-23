package modelo;

import exceptions.NameNotFoundException;
import exceptions.ProductIDNotFoundException;
import java.util.List;

/**
 *
 * @author Diego Ortega
 */
public interface IDAOProducto {
    
    //agregar, borrar, modificar, buscar, obtener por ID y devuelve el objeto producto,
    //get producto por nombre y devolver el objeto producto, get productos para obtener todos
    //los productos (arreglo de todos los productos)
    public boolean agregarProducto(Producto producto);
    public boolean borrarProducto(int id) throws ProductIDNotFoundException;
    public boolean modificarProducto(int id, Producto producto) throws ProductIDNotFoundException;
    public Producto getProductoById(int id) throws ProductIDNotFoundException;
    public Producto getProductoByName(String nombre) throws NameNotFoundException;
    public List getAllProductos(); 
    
    
}
