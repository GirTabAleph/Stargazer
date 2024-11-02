package controlador;

import exceptions.IDNotFoundException;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import modelo.Buscador;
import modelo.Cliente;
import modelo.Producto;
import modelo.Vendedor;
import modelo.Venta;

public class ControladorVenta {

    private Producto[] productos;
    private Cliente[] clientes;
    private Vendedor[] vendedores;

    public ControladorVenta(Producto[] productos, Cliente[] clientes, Vendedor[] vendedores) {
        this.productos = productos;
        this.clientes = clientes;
        this.vendedores = vendedores;
    }

    public Venta crearVenta() throws IDNotFoundException {
        
        try {
            
            // Seleccionar producto por ID
            int productoId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del producto:"));
            
            try{
                
                //Esto se supone tira la excepción, ver el código de buscarProductoPorId.
                Producto producto = Buscador.buscarProductoPorId(productos, productoId);
                
                double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad vendida:"));
                LocalDateTime fechaVenta = LocalDateTime.now(); // Fecha y hora actuales
                String zona = JOptionPane.showInputDialog("Ingrese la zona de venta:");

                // Seleccionar cliente por ID
                int clienteId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del cliente:"));
                Cliente cliente = Buscador.buscarClientePorId(clientes, clienteId);

                if (cliente == null) {
                    JOptionPane.showMessageDialog(null, "El cliente seleccionado no existe.");
                    return null;
                }

                // Seleccionar vendedor por ID
                int vendedorId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del vendedor:"));
                Vendedor vendedor = Buscador.buscarVendedorPorId(vendedores, vendedorId);

                if (vendedor == null) {
                    JOptionPane.showMessageDialog(null, "El vendedor seleccionado no existe.");
                    return null;
                }

                // Preguntar si requiere factura
                int respuestaFactura = JOptionPane.showConfirmDialog(null, "¿Requiere factura?", "Factura", JOptionPane.YES_NO_OPTION);
                boolean requiereFactura = (respuestaFactura == JOptionPane.YES_OPTION);

                // Crear y devolver la venta
                return Venta.crearVenta(producto, cantidad, fechaVenta, zona, cliente, vendedor, requiereFactura);

            } catch(IDNotFoundException ex){
                
                JOptionPane.showMessageDialog(null, "El producto seleccionado no existe.");
                return null;
                
            }
            
        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese números donde se requiera.");
            return null;
        
        }
    
    }
    
}


