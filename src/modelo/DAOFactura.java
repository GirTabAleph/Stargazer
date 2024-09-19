package modelo;

import exceptions.IDNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOFactura implements IDAOFactura {
    
    //Atributos.
    private List dtoFacturas = new ArrayList();

    @Override
    public boolean insertarFactura(Factura factura) {
        
        return dtoFacturas.add(factura);

    }
    
    //What the fuck does this do???
    @Override
    //Tratar el número de la factura como ID.
    public Factura obtenerFactura(Factura factura) throws IDNotFoundException{
        
        Factura facturaObtenida = null;
        int posicion = buscarPorNumFactura(factura.getNumeroFactura());
        
        if(posicion != -1){
            
            facturaObtenida = (Factura)dtoFacturas.get(posicion);
            return facturaObtenida;
            
        } else {
            
            throw new IDNotFoundException("El número de factura " + 
                    factura.getNumeroFactura() + " no se encontró.");
            
        }

    }

    @Override
    public boolean eliminarFactura(Factura factura) throws IDNotFoundException{
        
        int posicion = buscarPorNumFactura(factura.getNumeroFactura());
        
        if(posicion == -1){
            
            throw new IDNotFoundException("El número de factura " 
                    + factura.getNumeroFactura() + " no existe.");
            
        } else {
            
           dtoFacturas.remove(posicion);
           return true;
            
        }

    }

    @Override
    //Esta actualización es la modificación.
    public boolean actualizarFactura(Factura factura) throws IDNotFoundException{
        
        int posicion = buscarPorNumFactura(factura.getNumeroFactura());
        
        if(posicion == -1){
            
            throw new IDNotFoundException("La factura con número " +
                    factura.getNumeroFactura() + " no existe.");
            
            
        } else {
            
            dtoFacturas.set(posicion, factura);
            return true;
            
        }

    }
    
    public int buscarPorNumFactura(int numFactura){
        
        int posicion = 0;
        boolean encontrado = false;
        
        //Buscar.
        while(posicion <= dtoFacturas.size() && !encontrado){
            
            Factura factura = (Factura)(dtoFacturas.get(posicion));
            
            if(factura.getNumeroFactura() == numFactura){
                
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
    
}
