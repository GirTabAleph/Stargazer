/* package modelo;

import exceptions.NameNotFoundException;
import exceptions.ProductIDNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class DAOProductoHashMap implements IDAOProducto{
    
    //Crear el hashmap.
    private HashMap <Integer, Producto> productos = new HashMap<>();
    
    @Override
    public boolean agregarProducto(Producto producto) {
        
        if(productos.put(producto.getId(), producto).equals(producto)){
            
            return true;
            
        } else {
            
            return false;
            
        }
        
    }

    @Override
    public boolean borrarProducto(int id) throws ProductIDNotFoundException {
        
        if(productos.get(id) == null){
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            productos.remove(id);
            return true;
            
        }
        
    }

    @Override
    public boolean modificarProducto(int id, Producto producto) throws ProductIDNotFoundException {
        
        if(productos.get(id) == null){
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            productos.replace(id, producto);
            return true;
            
        }
        
    }

    @Override
    public Producto getProductoById(int id) throws ProductIDNotFoundException {
        
        Producto producto = productos.get(id);
        
        if(producto == null){
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            return producto;
            
        }
        
    }

    @Override
    public Producto getProductoByName(String nombre) throws NameNotFoundException {
        
        Producto producto = null;
        
        for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
            
            producto = entry.getValue();
            
            if(producto.getNombre().equals(nombre)){
                
                break;
                
            }
            
        }
        
        if(!producto.getNombre().equals(nombre)){
            
            throw new NameNotFoundException(nombre);
            
        } else {
            
            return producto;
            
        }
        
    }

    @Override
    public Object[][] getAllProductos() {
        
        Object[][] matrizProductos = new Object[productos.size()][11];
        int pos = 0;
        Producto producto;
        
        for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
            
            producto = entry.getValue();
            matrizProductos[pos][0] = producto.getId();
            matrizProductos[pos][1] = producto.getNombre();
            matrizProductos[pos][2] = producto.getUbicacion();
            matrizProductos[pos][3] = producto.getPrecio();
            matrizProductos[pos][4] = producto.getCosto();
            matrizProductos[pos][5] = producto.getDescuento();
            matrizProductos[pos][6] = producto.getCategoria();
            matrizProductos[pos][7] = producto.getProveedor();
            matrizProductos[pos][8] = producto.getStockMin();
            matrizProductos[pos][9] = producto.getStockMax();
            matrizProductos[pos][10] = producto.getExistencias();
            
            pos++;
            
        }
        
        return matrizProductos;
        
    }
    
}

*/


package modelo;

import exceptions.NameNotFoundException;
import exceptions.ProductIDNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class DAOProductoHashMap implements IDAOProducto {
    
    private HashMap<Integer, Producto> productos = new HashMap<>();

    @Override
    public boolean agregarProducto(Producto producto) {
        
        if (producto == null) {
            
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        
        
        Producto productoExistente = productos.put(producto.getId(), producto);
        
        return productoExistente == null;
    }

    @Override
    public boolean borrarProducto(int id) throws ProductIDNotFoundException {
       
        if (productos.get(id) == null) {
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            productos.remove(id);
            
            return true;
            
        }
        
    }
    

    @Override
    public boolean modificarProducto(int id, Producto producto) throws ProductIDNotFoundException {
        
        if (productos.get(id) == null) {
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            productos.replace(id, producto);
            
            return true;
            
        }
        
    }
    

    @Override
    public Producto getProductoById(int id) throws ProductIDNotFoundException {
        
        Producto producto = productos.get(id);
        
        if (producto == null) {
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            return producto;
            
        }
        
    }
    

    @Override
    public Producto getProductoByName(String nombre) throws NameNotFoundException {
        
        Producto producto = null;
        
        for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
            
            producto = entry.getValue();
            
            if (producto.getNombre().equals(nombre)) {
                break;
                
            }
            
        }
        
        if (producto == null || !producto.getNombre().equals(nombre)) {
            
            throw new NameNotFoundException(nombre);
            
        } else {
            
            return producto;
            
        }
        
    }

    @Override
    public Object[][] getAllProductos() {
        Object[][] matrizProductos = new Object[productos.size()][11];
        int pos = 0;
        Producto producto;
        
        for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
            producto = entry.getValue();
            matrizProductos[pos][0] = producto.getId();
            matrizProductos[pos][1] = producto.getNombre();
            matrizProductos[pos][2] = producto.getUbicacion();
            matrizProductos[pos][3] = producto.getPrecio();
            matrizProductos[pos][4] = producto.getCosto();
            matrizProductos[pos][5] = producto.getDescuento();
            matrizProductos[pos][6] = producto.getCategoria();
            matrizProductos[pos][7] = producto.getProveedor();
            matrizProductos[pos][8] = producto.getStockMin();
            matrizProductos[pos][9] = producto.getStockMax();
            matrizProductos[pos][10] = producto.getExistencias();
            
            pos++;
            
        }
        
        return matrizProductos;
        
    }
    
}
