package modelo;

public interface IDAOVenta {
    
    public boolean insertarVenta(NuevaVenta venta);
    
    public boolean cancelarVenta(int idVenta);
    
    public boolean buscarVenta(int idVenta);
    
    public NuevaVenta getVenta(int idVenta);
    
}
