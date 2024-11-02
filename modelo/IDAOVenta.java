package modelo;

import exceptions.IDNotFoundException;

public interface IDAOVenta {
    
    public boolean insertarVenta(NuevaVenta venta);
    
    public boolean cancelarVenta(int idVenta) throws IDNotFoundException;
    
    public int buscarVenta(int idVenta);
    
    public NuevaVenta getVenta(int idVenta);
    
}
