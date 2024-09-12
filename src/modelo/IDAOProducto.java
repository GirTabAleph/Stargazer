
package modelo;

import exceptions.IDNotFoundException;
import exceptions.NameNotFoundException;

/**
 *
 * @author Diego Ortega
 */
public interface IDAOProducto {
    
    //agregar, borrar, modificar, buscar, obtener por ID y devuelve el objeto producto,
    //get producto por nombre y devolver el objeto producto, get productos para obtener todos
    //los productos (arreglo de todos los productos)
    public boolean agregarProducto(Producto producto);
    public boolean borrarProducto(int id) throws IDNotFoundException;
    public boolean modificarProducto(int id, Producto producto) throws IDNotFoundException;
    public Producto getProductoById(int id) throws IDNotFoundException;
    public Producto getProductoByName(String nombre) throws NameNotFoundException;
    public Producto[] getAllProductos(); 
    
    
}
