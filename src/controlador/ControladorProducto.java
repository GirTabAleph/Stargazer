package controlador;

import exceptions.IDNotFoundException;
import exceptions.ProductIDNotFoundException;
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
                        producto = iu.leerProducto(0);
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
                        
                        idProducto = iu.leerIdProducto("modificar");
                        producto = iu.leerProducto(idProducto);
                        
                        if(daoProducto.modificarProducto(idProducto, producto)){
                            
                            iu.mensaje("Producto " + producto + " modificado correctamente.");
                            
                        } else {
                            
                            iu.mensaje("Alguna otra cosa salió mal.");
                            
                        }
                        
                        break;
                        
                    case 4:
                        
                        iu.mensaje("Listado de todos los productos.");
                        iu.mostrarListaProductos(daoProducto.getAllProductos() );
                        break;
                        
                    default:
                        
                        iu.mensaje("Opción no reconocida. Intente nuevamente.");
                        break;

                }
                
            }catch(ProductIDNotFoundException ex){
                
                System.out.println(ex.getMessage());
            
                /*
            } catch(IDNotFoundException ex2){
                
                System.out.println(ex2.getMessage());
             */   
            }
            
        } while(opcion != 0);
        
    }
    
    public static void main(String args[]){
        
        //Crear el objeto de control.
        new ControladorProducto();
        
    }
    
    
    
}
