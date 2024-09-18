package modelo;

import exceptions.IDNotFoundException;

public interface IDAOFactura {
    
    boolean insertarFactura(Factura factura);
    Factura obtenerFactura(Factura factura) throws IDNotFoundException;
    boolean eliminarFactura(Factura factura) throws IDNotFoundException;
    boolean actualizarFactura(Factura factura) throws IDNotFoundException;
    
}
