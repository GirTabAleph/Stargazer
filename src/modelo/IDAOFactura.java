package modelo;

public interface IDAOFactura {
    
    boolean insertarFactura(Factura factura);
    Factura obtenerFactura(int numeroFactura);
    boolean eliminarFactura(int numeroFactura);
    boolean actualizarFactura(Factura factura);
    
}
