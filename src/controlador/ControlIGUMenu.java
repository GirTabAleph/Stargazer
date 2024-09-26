package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.IGUMenu;
import vista.IGUProductos;

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
                
                IGUProductos ventana = new IGUProductos();
                ventana.setVisible(true);
                igu.getEscritorio().add(ventana);
                
                break;
                
            default:
                
                assert(true);
                break;
        }
        
    }
    
}
