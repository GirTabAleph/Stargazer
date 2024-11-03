package modelo;

import java.util.Date;

/**
 *
 * @author Diego Ortega
 */
public interface PersonaFactory {
    
    public Persona crearPersona(int idPersona, String nombre, String rfc, 
            String telefono, String domicilio, int idZona, Date fecha,
            Double porcentaje);
    
}
