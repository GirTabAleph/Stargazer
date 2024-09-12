package modelo;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Entrega {
    
    public static void main(String[] args) {

        List<Producto> productos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Vendedor> vendedores = new ArrayList<>();


        Venta venta = Venta.crearVenta(productos.toArray(new Producto[0]), clientes.toArray(new Cliente[0]), vendedores.toArray(new Vendedor[0]));


        if (venta.requiereFactura()) {
            int numeroFactura = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la factura:"));
            Factura factura = new Factura(venta.getCliente(), numeroFactura, venta.getFechaVenta(), productos);
            JOptionPane.showMessageDialog(null, factura.generarFactura());
        }
    }
}
