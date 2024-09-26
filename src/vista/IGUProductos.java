package vista;

import controlador.ControlIGUProductos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.LineBorder;
import modelo.Producto;

/**
 *
 * @author Diego Ortega
 */
public class IGUProductos extends JInternalFrame{
    
    private JLabel[] etiquetas = {
    
        new JLabel("ID: "),
        new JLabel ("Nombre: "),
        new JLabel ("Ubicacion"),
        new JLabel ("Precio: "),
        new JLabel ("Costo: "),
        new JLabel ("Descuento: "),
        new JLabel ("Categoría: "),
        new JLabel ("Proveedor"),
        new JLabel ("Stock mínimo: "),
        new JLabel ("Stock máximo: "),
        new JLabel ("Existencias: ")
            
    };
    
    private JTextField[] campos = {
    
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10)

    };
    
    private JButton btnAceptar = new JButton("Aceptar"),
            btnCancelar = new JButton("Cancelar"),
            btnLimpiar = new JButton("Limpiar campos");
    
    //Definir control apra esta ventana.
    private ControlIGUProductos controlProductos = new ControlIGUProductos(this);
    
    //Construir ventana en el constructor.
    public IGUProductos(){
        
        //redimensionable
        super("Catálogo de productos", true, true, true, true);
        setSize(590, 390);
        add(getPanelDatos(), BorderLayout.CENTER);
        
        add(getPanelBotones(), BorderLayout.SOUTH);
                
    }
    
    public JPanel getPanelDatos(){
        
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(11, 2));
        //Agregar componentes.
        
        for(int pos = 0; pos < etiquetas.length; pos++){
            
            panelDatos.add(etiquetas[pos]);
            panelDatos.add(campos[pos]);
            
        }
        
        return panelDatos;
        
    }
    
    public JPanel getPanelBotones(){
        
        JPanel panelBotones = new JPanel();
        Box cajaBotones = Box.createHorizontalBox();
        cajaBotones.add(Box.createHorizontalStrut(15));
        cajaBotones.add(btnAceptar);
        cajaBotones.add(Box.createHorizontalStrut(10));
        cajaBotones.add(btnCancelar);
        cajaBotones.add(Box.createHorizontalStrut(10));
        cajaBotones.add(btnLimpiar);
        
        panelBotones.setBorder(new LineBorder(Color.BLUE));
        panelBotones.add(cajaBotones);
        
        btnAceptar.addActionListener(controlProductos);
        btnCancelar.addActionListener(controlProductos);
        btnLimpiar.addActionListener(controlProductos);
        
        return panelBotones;
        
    }
    
    public Producto getProducto(){
        
        /*
        int id, String nombre, String ubicacion, double precio, double costo,
                    double descuento, String categoria, int proveedor, int stockMin,
                    int stockMax, int existencias
        */
        
        return new Producto(Integer.parseInt(campos[0].getText()),
                            campos[1].getText(),
                            campos[2].getText(),
                            Double.parseDouble(campos[3].getText()),
                            Double.parseDouble(campos[4].getText()),
                            Double.parseDouble(campos[5].getText()),
                            campos[6].getText(),
                            Integer.parseInt(campos[7].getText()),
                            Integer.parseInt(campos[8].getText()),
                            Integer.parseInt(campos[9].getText()),
                            Integer.parseInt(campos[10].getText())
        );
        
    }
    
}
