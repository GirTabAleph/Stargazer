package modelo;

import exceptions.IDNotFoundException;

public interface IDAOVenta {
    
    public boolean insertarVenta(Venta venta);
    
    public boolean cancelarVenta(int idVenta, java.util.Date fechaCancelacion, String motivoCancel) throws IDNotFoundException;
        
    public Venta getVenta(int idVenta) throws IDNotFoundException;
    
}
