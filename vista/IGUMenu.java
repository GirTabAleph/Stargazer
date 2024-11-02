package vista;

import controlador.ControlIGUMenu;
import javax.swing.*;

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
        
        new IGUMenu();
        
    }
    
}