package modelo;

import exceptions.IDNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOVentasArrayList implements IDAOVenta{
    
    //Atributos para las ventas.
    private List ventas = new ArrayList();
    private Buscador buscador = new Buscador();

    
    public boolean insertarVenta(NuevaVenta venta){
        
        return ventas.add(venta);
        
    }
    
    public boolean cancelarVenta(int idVenta) throws IDNotFoundException{
        
        //Quitar la venta en la posición que la búsqueda arroje.
        int posicion = buscarVenta(idVenta);
        
        if(posicion != 1){
            
            ventas.remove(posicion);
            return true;
        
        } else {
            
            throw new IDNotFoundException(idVenta);
            
        }
        
    }
    
    public int buscarVenta(int idVenta){
        
        //Jesus Christ this is awful.
        int posicion = 0;
        boolean encontrado = false;
        
        //Buscar.
        while(posicion <= ventas.size() && !encontrado){
            
            NuevaVenta venta = (NuevaVenta)(ventas.get(posicion));
            
            if(venta.getIdVenta() == idVenta){
                
                encontrado = true;
            
            }//Fin if
            
            posicion++;
        
        }//Fin while       
        
        if(encontrado){
        
            return posicion;
            
        } else {
            
            return -1;
            
        }
    
    }
    
    //¿Qué hace esto?
    
    //Llamar al buscador.
    public NuevaVenta getVenta(int idVenta){
        
        //Este try se debe a que el buscador tira excepciones de ID no encontrada.
        try {
            
            NuevaVenta ventaARegresar = buscador.buscarVentaPorId((NuevaVenta[])ventas.toArray(), idVenta);
            return ventaARegresar;
            
        } catch (IDNotFoundException ex) {
        
            ex.getMessage();
            return null;
            
        }        
    
    }

}
