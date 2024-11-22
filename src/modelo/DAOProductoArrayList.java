package modelo;

import exceptions.NameNotFoundException;
import exceptions.ProductIDNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class DAOProductoArrayList implements IDAOProducto{
    
    private List productos = new ArrayList();

    @Override
    public boolean agregarProducto(Producto producto) {
        
        return productos.add(producto);
        
    }

    @Override
    public boolean borrarProducto(int id) throws ProductIDNotFoundException{
        
        int posicion = buscarPorId(id);
        
        if(posicion == -1){
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
           productos.remove(posicion);
           return true;
            
        }
        
    }

    @Override
    public boolean modificarProducto(int id, Producto producto) throws ProductIDNotFoundException {
        
        int posicion = buscarPorId(id);
        
        if(posicion == -1){
            
            throw new ProductIDNotFoundException(id);
            
            
        } else {
            
            productos.set(posicion, producto);
            return true;
            
        }
        
    }

    @Override
    public Producto getProductoById(int id) throws ProductIDNotFoundException {
        
        int posicion = buscarPorId(id);
        Producto producto = null;
        
        if(posicion == -1){
            
            throw new ProductIDNotFoundException(id);
            
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
    public Object[][] getAllProductos() {
        
        Object[][] arregloProductos = new Producto[productos.size()][11];
        
        int pos = 0;
        
        for(Producto producto : (ArrayList<Producto>)productos){
   
            
            arregloProductos[pos][0] = producto.getId();
            arregloProductos[pos][1] = producto.getNombre();
            arregloProductos[pos][2] = producto.getUbicacion();
            arregloProductos[pos][3] = producto.getPrecio();
            arregloProductos[pos][4] = producto.getCosto();
            arregloProductos[pos][5] = producto.getDescuento();
            arregloProductos[pos][6] = producto.getCategoria();
            arregloProductos[pos][7] = producto.getProveedor();
            arregloProductos[pos][8] = producto.getStockMin();
            arregloProductos[pos][9] = producto.getStockMax();
            arregloProductos[pos][10] = producto.getExistencias();
            
            pos++;
            
        }
        
        return arregloProductos;
        
    }
    
    public int buscarPorId(int id){
        
        int posicion = 0;
        boolean encontrado = false;
        
        //Buscar.
        while(posicion < productos.size() && !encontrado){
            
            Producto producto = (Producto)(productos.get(posicion));
            
            if(producto.getId() == id){
                
                encontrado = true;
            
            } else {
               
                //Incremento si no se encontró.
                posicion++;
                
            }
           
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



/*

package modelo;

import exceptions.ProductIDNotFoundException;
import exceptions.NameNotFoundException;
import iteratorPrueba.IProductoIterator;
import iteratorPrueba.ProductoListIterator;
import java.util.ArrayList;
import java.util.List;

public class DAOProductoArrayList implements IDAOProducto {
    
    private List<Producto> productos = new ArrayList<>();

    @Override
    public boolean agregarProducto(Producto producto) {
        
        return productos.add(producto);
        
    }

    @Override
    public boolean borrarProducto(int id) throws ProductIDNotFoundException {
        
        int posicion = buscarPorId(id);
        
        if (posicion == -1) {
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            productos.remove(posicion);
            
            return true;
            
        }
        
    }

    @Override
    public boolean modificarProducto(int id, Producto producto) throws ProductIDNotFoundException {
        
        int posicion = buscarPorId(id);
        
        if (posicion == -1) {
            
            throw new ProductIDNotFoundException(id);
            
        } else {
            
            productos.set(posicion, producto);
            return true;
            
        }
        
    }

    @Override
    public Producto getProductoById(int id) throws ProductIDNotFoundException {
        
        int posicion = buscarPorId(id);
        
        if (posicion == -1) {
            
            throw new ProductIDNotFoundException(id);
            
        }
        
        return productos.get(posicion);
        
    }

    @Override
    public Producto getProductoByName(String nombre) throws NameNotFoundException {
        
        for (Producto producto : productos) {
            
            if (producto.getNombre().equals(nombre)) {
                
                return producto;
                
            }
            
        }
        
        throw new NameNotFoundException(nombre);
        
    }

    @Override
    public Object[][] getAllProductos() {
        
        Object[][] arregloProductos = new Producto[productos.size()][11];
        
        int pos = 0;
        
        for (Producto producto : productos) {
            
            arregloProductos[pos][0] = producto.getId();
            arregloProductos[pos][1] = producto.getNombre();
            arregloProductos[pos][2] = producto.getUbicacion();
            arregloProductos[pos][3] = producto.getPrecio();
            arregloProductos[pos][4] = producto.getCosto();
            arregloProductos[pos][5] = producto.getDescuento();
            arregloProductos[pos][6] = producto.getCategoria();
            arregloProductos[pos][7] = producto.getProveedor();
            arregloProductos[pos][8] = producto.getStockMin();
            arregloProductos[pos][9] = producto.getStockMax();
            arregloProductos[pos][10] = producto.getExistencias();
            pos++;
            
        }
        
        return arregloProductos;
        
    }
    
    public int buscarPorId(int id){
        
        int posicion = 0;
        boolean encontrado = false;
        
        //Buscar.
        while(posicion < productos.size() && !encontrado){
            
            Producto producto = (Producto)(productos.get(posicion));
            
            if(producto.getId() == id){
                
                encontrado = true;
            
            } else {
               
                //Incremento si no se encontró.
                posicion++;
                
            }
           
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
    
    public IProductoIterator getIterator() {
        
        return new ProductoListIterator(productos);
        
    }

    
}
