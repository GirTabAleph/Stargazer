package vista;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Ortega
 */
public abstract class VentanaInterna extends JInternalFrame {

    public VentanaInterna(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
    }
    
    
    
    public void mensaje(String texto){
        
        JOptionPane.showMessageDialog(this, texto, "Aviso", JOptionPane.PLAIN_MESSAGE);
        
    }
    
    public void mensaje(String texto, String titulo){
        
        JOptionPane.showMessageDialog(this, texto, titulo, JOptionPane.PLAIN_MESSAGE);
        
    }
    
    public void mensajeError(String texto){
        
        JOptionPane.showMessageDialog(this, texto, "Error", JOptionPane.ERROR_MESSAGE);
        
    }
    
    public void mensajeError(String texto, String titulo){
        
        JOptionPane.showMessageDialog(this, texto, titulo, JOptionPane.ERROR_MESSAGE);
        
    }
    
    public int confirmacion(String texto){
        
        return JOptionPane.showConfirmDialog(this, texto);
        
    }
    
}
