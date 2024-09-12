package modelo;

import javax.swing.JOptionPane;
import exceptions.IDNotFoundException;

public class Buscador {

    // Método para buscar un producto por ID
    public static Producto buscarProductoPorId(Producto[] productos, int id) throws IDNotFoundException{
        
            
            for (Producto producto : productos) {
            
                if (producto.getId() == id) {

                    return producto;

                }

            }
            
            //Si el ID nunca se encontró, tirar la excepción.
            throw new IDNotFoundException("El producto de ID " + id +
                    " no se ha encontrado.");
            
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            //return null; 
    }

    // Método para buscar un cliente por ID
    public static Cliente buscarClientePorId(Cliente[] clientes, int id) throws IDNotFoundException{
        
        for (Cliente cliente : clientes) {
        
            if (cliente.getIdCliente() == id) {
            
                return cliente;
            
            }
        
        }
        //JOptionPane.showMessageDialog(null, "Cliente con ID " + id + " no encontrado.");
        //Si el ID nunca se encontró, tirar la excepción.
            throw new IDNotFoundException("El cliente de ID " + id +
                    " no se ha encontrado.");
    }

    // Método para buscar un vendedor por ID
    public static Vendedor buscarVendedorPorId(Vendedor[] vendedores, int id) throws IDNotFoundException{
        
        for (Vendedor vendedor : vendedores) {
        
            if (vendedor.getIdVendedor() == id) {
            
                return vendedor;
            
            }
        
        }
        //JOptionPane.showMessageDialog(null, "Vendedor con ID " + id + " no encontrado.");
        //Si el ID nunca se encontró, tirar la excepción.
            throw new IDNotFoundException("El vendedor de ID " + id +
                    " no se ha encontrado.");
    }
}
