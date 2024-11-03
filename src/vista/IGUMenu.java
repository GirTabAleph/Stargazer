package vista;

import controlador.ControlIGUMenu;
import java.util.Calendar;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import modelo.Cliente;
import modelo.ClienteFactory;
import modelo.Vendedor;
import modelo.VendedorFactory;

/**
 *
 * @author Diego Ortega
 */

//Esta cosa es un frame.
public class IGUMenu extends JFrame{
    
    private JMenuBar barraMenu = new JMenuBar();
    private JMenu menuApp = new JMenu("Aplicación"), menuCatalogos = new JMenu("Catálogos");
    
    private JMenuItem miSalir = new JMenuItem("Salir"); //para menuApp
    private JMenuItem miProductos = new JMenuItem("Productos"); //para menuCatalogos
    
    private ControlIGUMenu control = new ControlIGUMenu(this);
    
    private JDesktopPane escritorio = new JDesktopPane();
    
    public IGUMenu(){
        
        super("Aplicación ventas V.0.0");
        
        add(escritorio);
        
        setJMenuBar(getBarraMenu());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        setVisible(true);
        
    }
    
    public JMenuBar getBarraMenu(){
        
        //Establecer el control.
        miSalir.addActionListener(control);
        miProductos.addActionListener(control);
        
        //Inicializar menús.
        menuApp.add(miSalir);
        menuCatalogos.add(miProductos);
        
        //Asignar menús.
        barraMenu.add(menuApp);
        barraMenu.add(menuCatalogos);
        
        return barraMenu;
        
    }
    
    public JDesktopPane getEscritorio(){
        
        return escritorio;
        
    }
    
    public static void main(String args[]){
        
        VendedorFactory factoryVendedor = new VendedorFactory();
        ClienteFactory factoryCliente = new ClienteFactory();
        
        //(idPersona, nombre, rfc, telefono, domicilio, idZona, fecha, porcentaje
        
        Vendedor v = factoryVendedor.crearPersona(1, "Juanjo", "CCC123CCC123C", "5526316817", "Atrás de la FCA",
                1, Calendar.getInstance().getTime(), 10.0);
        
        Cliente c = factoryCliente.crearPersona(2, "Ernesto", "123DDD123DDD1", "5515444175", "En frente de la FCA",
                2, Calendar.getInstance().getTime(), null);
        
        System.out.println(v.toString());
        System.out.println(c.toString());
        
        new IGUMenu();
        
    }
    
}
