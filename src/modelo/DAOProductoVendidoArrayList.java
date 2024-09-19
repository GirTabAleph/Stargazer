package modelo;


import java.util.*;


public class DAOProductoVendidoArrayList implements IDAOProductoVendido{
    
    private List<ProductoVendido> dtoListaProductos = new ArrayList<>();
    
    public DAOProductoVendidoArrayList(){
    
    }
    
    public boolean insertarProductoVendido(ProductoVendido producto){
    
        return dtoListaProductos.add(producto);
        
    }
  
    public boolean borrarProductoVendido(int idProducto){
        
        dtoListaProductos.remove(idProducto); //Como borrar??
        return true;
    
    }
    
    
    
}
