package modelo;

import exceptions.IDNotFoundException;

/**
 *
 * @author Diego Ortega
 */
public interface IBuscador {
    
    // Método para buscar un producto por ID
    public Producto buscarProductoPorId(Producto[] productos, int id) throws IDNotFoundException;
    
    // Método para buscar un cliente por ID
    public Cliente buscarClientePorId(Cliente[] clientes, int id) throws IDNotFoundException;
    
    // Método para buscar un vendedor por ID
    public Vendedor buscarVendedorPorId(Vendedor[] vendedores, int id) throws IDNotFoundException;
    
    public NuevaVenta buscarVentaPorId(NuevaVenta[] ventas, int id) throws IDNotFoundException;
    
}
