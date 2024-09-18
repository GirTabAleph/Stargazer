package modelo;

import exceptions.IDNotFoundException;
import exceptions.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego Ortega
 */
public class DAOProductoArrayList implements IDAOProducto{
    
    private List productos = new ArrayList();

    @Override
    public boolean agregarProducto(Producto producto) {
        
        return productos.add(producto);
        
    }

    @Override
    public boolean borrarProducto(int id) throws IDNotFoundException {
        
        int posicion = buscarPorId(id);
        
        if(posicion == -1){
            
            throw new IDNotFoundException(id);
            
        } else {
            
           productos.remove(posicion);
           return true;
            
        }
        
    }

    @Override
    public boolean modificarProducto(int id, Producto producto) throws IDNotFoundException {
        
        int posicion = buscarPorId(id);
        
        if(posicion == -1){
            
            throw new IDNotFoundException(id);
            
            
        } else {
            
            productos.set(posicion, producto);
            return true;
            
        }
        
    }

    @Override
    public Producto getProductoById(int id) throws IDNotFoundException {
        
        int posicion = buscarPorId(id);
        Producto producto = null;
        
        if(posicion == -1){
            
            throw new IDNotFoundException(id);
            
        } else {
            
            producto = (Producto)productos.get(posicion);
            return producto;
            
        }
        
    }

    @Override
    public Producto getProductoByName(String nombre) throws NameNotFoundException{
        
        int posicion = buscarPorNombre(nombre);
        Producto producto = null;
        
        if(posicion == -1){
            
            throw new NameNotFoundException(nombre);
            
        } else {
            
            producto = (Producto)productos.get(posicion);
            return producto;
            
        }
        
    }

    @Override
    public Producto[] getAllProductos() {
        
        Producto[] productosArray = new Producto[productos.size()];
        
        productosArray = (Producto[])productos.toArray();
        
        return productosArray;
        
    }
    
    public int buscarPorId(int id){
        
        int posicion = 0;
        boolean encontrado = false;
        
        //Buscar.
        while(posicion <= productos.size() && !encontrado){
            
            Producto producto = (Producto)(productos.get(posicion));
            
            if(producto.getId() == id){
                
                encontrado = true;
            
            }//Fin if
            
            posicion++;
        
        }//Fin while       
        
        if(encontrado){
        
            return posicion;
            
        } else {
            
            return -1;
            
        }
        
    }
    
    public int buscarPorNombre(String nombre){
        
        boolean encontrado = false;
        int posicion = 0;
        
        while(posicion <= productos.size() && !encontrado){
            
            Producto producto = (Producto)(productos.get(posicion));
            
            if(nombre.equals(producto.getNombre())){
                
                encontrado = true;
                
            }//Fin if.
            
            posicion++;
            
        }
        
        if(encontrado){
            
            return posicion;
            
        } else {
            
            return -1;
            
        } //Fin if
        
    }
    
}
