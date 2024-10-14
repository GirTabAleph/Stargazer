package controlador;

import exceptions.NegativeIntegerException;
import exceptions.VendorIDNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.DAOProductoArrayList;
import modelo.IDAOProducto;
import modelo.Producto;
import vista.IGUProductos;
import vista.IGUVendedor;
import vista.IUTextoProducto;

/**
 *
 * @author Diego Ortega
 */
public class ControlIGUVendedor implements ActionListener{
    
    private IGUVendedor igu;
    
    public ControlIGUVendedor(IGUVendedor igu){
        
        this.igu = igu;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String cmd = e.getActionCommand();
        
        //A quien le toque vista y modelo: implementen estos métodos por favor.
        //vendedores es un dao de vendedores tomado como arraylist.
        switch(cmd){
            
                case "Aceptar":
                    /*
                    vendedores.agregarVendedor(igu.getVendedor);
                    igu.limpiarCampos();
                    igu.activarBarra();
                    igu.desactivarBotones();
                    */
                    break;
            
                case "Eliminar":
                    /*
                    vendedores.borrarVendedor(igu.getId);
                    igu.limpiarCampos();
                    igu.activarBarra();
                    igu.desactivarBotones();
                    */
                    break;
                    
                case "Cancelar":
                    igu.dispose();
                    break;
            
            
        }
        
    }
    
}
