package modelo;


import java.util.*;


public class DAOProductoVendidoArrayList implements IDAOProductoVendido{
    
    private List<ProductoVendido> listaProductos = new ArrayList<>();
    
    public DAOProductoVendidoArrayList(){
    
    }
    
    public boolean insertarProductoVendido(ProductoVendido producto){
    
        return listaProductos.add(producto);
        
    }
  
    public boolean borrarProductoVendido(int idProducto){
        
        listaProductos.remove(idProducto);
        return true;
    
    }
    
    
    
}
