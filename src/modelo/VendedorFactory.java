package modelo;

import java.util.Date;

/**
 *
 * @author Diego Ortega
 */
public class VendedorFactory implements PersonaFactory{

    @Override
    public Vendedor crearPersona(int idPersona, String nombre, String rfc, String telefono, String domicilio, int idZona, Date fecha, Double porcentaje) {
        
        //zona como Zona
        return new Vendedor(idPersona, nombre, rfc, telefono, domicilio,
            idZona, fecha, porcentaje);
        
    }
    
}
