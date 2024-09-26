package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.DAOProductoArrayList;
import modelo.IDAOProducto;
import vista.IGUProductos;
import vista.IUTextoProducto;

/**
 *
 * @author Diego Ortega
 */
public class ControlIGUProductos implements ActionListener{
    
    private IGUProductos igu;
    private IDAOProducto productos = new DAOProductoArrayList();
    
    public ControlIGUProductos(IGUProductos igu){
        
        this.igu = igu;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        
        switch(comando){
            
            case "Aceptar":
                productos.agregarProducto(igu.getProducto());
                IUTextoProducto texto = new IUTextoProducto(); 
                texto.mostrarListaProductos((List)productos);
                break;
                
            case "Cancelar":
            
            case "Limpiar":
        }
        
    }
    
}
