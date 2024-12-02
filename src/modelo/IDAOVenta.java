package modelo;

import exceptions.IDNotFoundException;

public interface IDAOVenta {
    
    public boolean insertarVenta(Venta venta);
    
    public boolean cancelarVenta(int idVenta) throws IDNotFoundException;
    
    public int buscarVenta(int idVenta);
    
    public Venta getVenta(int idVenta);
    
}
