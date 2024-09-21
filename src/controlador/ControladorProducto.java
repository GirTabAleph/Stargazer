package controlador;

import exceptions.IDNotFoundException;
import modelo.DAOProductoArrayList;
import modelo.IDAOProducto;
import modelo.Producto;
import vista.IUProducto;
import vista.IUTextoProducto;

/**
 *
 * @author Diego Ortega
 */
public class ControladorProducto {
    
    //Inversión de control. Se instancía con la implementación de interfaz.
    private IUProducto iu = new IUTextoProducto();
    private IDAOProducto daoProducto = new DAOProductoArrayList();

    public ControladorProducto() {
        
        int opcion;
        int idProducto;
        Producto producto;
        
        do{
           
            opcion = iu.menuProductos();
            
            try{

                switch(opcion){

                    case 1:
                        producto = iu.leerProducto();
                        if(daoProducto.agregarProducto(producto)){

                            iu.mensaje("Producto agregado correctamente.");

                        } else {

                            iu.mensaje("Algo salió mal, no se qué.");

                        }

                        break;

                    case 2:
                        idProducto = iu.leerIdProducto("borrar");
                        if(daoProducto.borrarProducto(idProducto)){

                            iu.mensaje("Producto " + idProducto + " borrado correctamente.");

                        } else {
                            
                            iu.mensaje("Algo salió mal, ni modo.");
                            
                        }
                        
                        break;
                    
                    case 3:
                        
                        iu.mensaje("Listado de todos los productos.");
                        Producto productos[] = daoProducto.getAllProductos();
                        break;

                }
                
            }catch(IDNotFoundException ex){
                
                System.out.println(ex.getMessage());
                
            }
            
        } while(opcion != 0);
        
    }
    
    
    
}
