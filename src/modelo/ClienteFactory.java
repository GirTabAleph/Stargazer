package modelo;

import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Diego Ortega
 */
public class ClienteFactory implements PersonaFactory{

    @Override
    //Poner zona como Zona.
    public Cliente crearPersona(int idPersona, String nombre, String rfc, String telefono, String domicilio, int idZona, Date fecha, Double porcentaje) {
        
        //new zona, atributos para zona.
        Calendar calendario = Calendar.getInstance();
        porcentaje = null;
        return new Cliente(idPersona, nombre, rfc, telefono, domicilio, idZona, fecha);
        
    }
    
}
