package modelo;

import exceptions.IDNotFoundException;

public class Buscador implements IBuscador{
    
    //Constructor vacío.
    public Buscador(){
    }

    @Override
    public Producto buscarProductoPorId(Producto[] productos, int id) throws IDNotFoundException {
                 
            for (Producto producto : productos) {
            
                if (producto.getId() == id) {

                    return producto;

                }

            }            
            //Si el ID nunca se encontró, tirar la excepción.
            throw new IDNotFoundException("El producto de ID " + id +
                    " no se ha encontrado.");    
        
    }

    @Override
    public Cliente buscarClientePorId(Cliente[] clientes, int id) throws IDNotFoundException {
        
        for (Cliente cliente : clientes) {
        
            if (cliente.getIdCliente() == id) {
            
                return cliente;
            
            }
        
        }
        //Si el ID nunca se encontró, tirar la excepción.
        throw new IDNotFoundException("El cliente de ID " + id +
                " no se ha encontrado.");
    }

    @Override
    public Vendedor buscarVendedorPorId(Vendedor[] vendedores, int id) throws IDNotFoundException {
        
        for (Vendedor vendedor : vendedores) {
        
            if (vendedor.getIdVendedor() == id) {
            
                return vendedor;
            
            }
        
        }
        //Si el ID nunca se encontró, tirar la excepción.
        throw new IDNotFoundException("El vendedor de ID " + id +
                " no se ha encontrado.");
        
    }
    
    @Override
    public NuevaVenta buscarVentaPorId(NuevaVenta[] ventas, int id) throws IDNotFoundException{
        
        for(NuevaVenta venta : ventas){
            
            if (venta.getIdVenta() == id){
                
                return venta;
                
            }
            
        }
        
        //Aquí mandamos la excepción.
        throw new IDNotFoundException("La venta de ID " + id +
                " no se ha encontrado.");      
        
    }

}
