package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.IGUMenu;
import vista.IGUProductos;
import vista.IGUVendedor;

/**
 *
 * @author Diego Ortega
 */
public class ControlIGUMenu implements ActionListener{
       
    private IGUMenu igu;
    
    public ControlIGUMenu(IGUMenu igu){
        
        this.igu = igu;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        
        switch(comando){
            
            case "Salir":
                
                System.exit(0);
                break;
                
            case "Productos":
                
                IGUProductos ventanaProductos = new IGUProductos();
                ventanaProductos.setVisible(true);
                igu.getEscritorio().add(ventanaProductos);
                break;
            
            case "Vendedor":
                
                IGUVendedor ventanaVendedor = new IGUVendedor();
                ventanaVendedor.setVisible(true);
                igu.getEscritorio().add(ventanaVendedor);
                break;
                
            default:
                
                //Cabrón quién puso esto? Qué hace?
                assert(true);
                break;
        }
        
    }
    
}
