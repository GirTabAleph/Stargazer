package modelo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InterfazPrincipal {

    public static void main(String[] args) {
        IDAOFactura daoFactura = new DAOFactura();


        JFrame frame = new JFrame("Sistema de Gestión");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        
        JLabel titleLabel = new JLabel("Sistema de Ventas Alfa 1.0", SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 400, 30); // Coordenadas y tamaño
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente y tamaño
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        frame.add(titleLabel);

        
        JButton btnVentas = new JButton("Ventas");
        btnVentas.setBounds(50, 60, 150, 40);

        JButton btnClientes = new JButton("Clientes");
        btnClientes.setBounds(200, 60, 150, 40);

        JButton btnProductos = new JButton("Productos");
        btnProductos.setBounds(50, 110, 150, 40);

        JButton btnFacturas = new JButton("Facturas");
        btnFacturas.setBounds(200, 110, 150, 40);

        JButton btnVendedores = new JButton("Vendedores");
        btnVendedores.setBounds(125, 160, 150, 40);

        frame.add(btnVentas);
        frame.add(btnClientes);
        frame.add(btnProductos);
        frame.add(btnFacturas);
        frame.add(btnVendedores);

        btnVentas.addActionListener(e -> {

            Cliente cliente = Cliente.crearCliente();
            Vendedor vendedor = Vendedor.crearVendedor();
            Producto[] productos = {};  
            
            Venta venta = Venta.crearVenta(productos, new Cliente[]{cliente}, new Vendedor[]{vendedor});
            JOptionPane.showMessageDialog(null, "Venta creada con éxito.");


            Factura factura = new Factura(cliente, 1, LocalDate.now(), List.of(productos));
            boolean exito = daoFactura.insertarFactura(factura);
            if (exito) {
                JOptionPane.showMessageDialog(null, "Factura creada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear la factura.");
            }
        });

        btnClientes.addActionListener(e -> {
            Cliente cliente = Cliente.crearCliente();
            JOptionPane.showMessageDialog(null, "Cliente creado: " + cliente.getNombre());
        });

        btnProductos.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Función de productos no implementada.");
        });

        btnFacturas.addActionListener(e -> {

            int numeroFactura = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de factura:"));
            Factura factura = daoFactura.obtenerFactura(numeroFactura);
            if (factura != null) {
                JOptionPane.showMessageDialog(null, factura.generarFactura());
            } else {
                JOptionPane.showMessageDialog(null, "Factura no encontrada.");
            }
        });

        btnVendedores.addActionListener(e -> {
            Vendedor vendedor = Vendedor.crearVendedor();
            JOptionPane.showMessageDialog(null, "Vendedor creado: " + vendedor.getNombre());
        });

 
        frame.setVisible(true);
    }
}

