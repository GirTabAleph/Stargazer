package controlador;

import exceptions.ProductIDNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.DAOProductoArrayList;
import modelo.DAOProductoHashMap;
import modelo.IDAOProducto;
import modelo.Producto;
import vista.IGUProductos;
import vista.IUTextoProducto;

/**
 * @author Israel
 */

public class ControlIGUProductos implements ActionListener{
    
    private IGUProductos igu;
    private IDAOProducto productos = new DAOProductoHashMap();
    
    public ControlIGUProductos(IGUProductos igu){
        
        this.igu = igu;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        
        try {
            
            switch(comando){


                case "Aceptar":
                    productos.agregarProducto(igu.getProducto(true));
                    IUTextoProducto texto = new IUTextoProducto(); 
                    igu.activarTabla();
                    break;

                case "Eliminar":

                    productos.borrarProducto(igu.getId());

                    IUTextoProducto texto1 = new IUTextoProducto(); 
                    
                    break;    

                case "Cancelar":
                    igu.dispose();
                    break;

                case "Limpiar campos":
                    igu.limpiarCampos();
                    break;

                case "Nuevo":
                    nuevo();
                    break;

                case "Borrar":
                    borrar();
                    break;

                case "Modificar":
                    modificar();
                    break;

                case "Buscar":                   
                    buscar();
                    break;
                    
                case "Editar":
                    editar();
                    break;

            }
        
        } catch (ProductIDNotFoundException ex) {
        
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void nuevo(){
    
        igu.desactivarID();
        igu.activarCampos();
        igu.cambiarAccion("Aceptar");
        
    }
    
    public void borrar(){
        
        igu.activarID();
        igu.desactivarCampos();
        igu.cambiarAccion("Eliminar");
    }
    
    public void editar(){
        
        igu.activarID();
        igu.desactivarCampos();
        igu.cambiarAccion("Buscar");   
    }
    
    public void buscar() throws ProductIDNotFoundException{
        
        Producto producto = productos.getProductoById(igu.getId());
        
        igu.setProducto(producto);
        igu.cambiarAccion("Modificar");
        
        igu.activarCampos();
        igu.desactivarID();
    }
    public void modificar() throws ProductIDNotFoundException{
        
        productos.modificarProducto(igu.getId(), igu.getProducto(false));
        
        IUTextoProducto texto1 = new IUTextoProducto(); 
        
    }
}
