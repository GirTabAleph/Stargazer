package modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase Persona
 * @author Israel
 */
public class PersonaTest {

    private Persona persona;

    @Before
    public void setUp() {
        persona = new Persona("Juan Pérez", "RFC123456789", 
                "Calle Falsa 123", "5551234567", "Zona Norte");
    }

    /**
     * Test del getter y setter de nombre
     */
    @Test
    public void testSetGetNombre() {
        System.out.println("Prueba de setNombre y getNombre");
        persona.setNombre("Carlos Ramírez");
        assertEquals("Carlos Ramírez", persona.getNombre());
    }

    /**
     * Test del getter y setter de RFC
     */
    @Test
    public void testSetGetRfc() {
        System.out.println("Prueba de setRfc y getRfc");
        persona.setRfc("RFC987654321");
        assertEquals("RFC987654321", persona.getRfc());
    }

    /**
     * Test del getter y setter de domicilio
     */
    @Test
    public void testSetGetDomicilio() {
        System.out.println("Prueba de setDomicilio y getDomicilio");
        persona.setDomicilio("Avenida Siempre Viva 742");
        assertEquals("Avenida Siempre Viva 742", persona.getDomicilio());
    }

    /**
     * Test del getter y setter de teléfono
     */
    @Test
    public void testSetGetTelefono() {
        System.out.println("Prueba de setTelefono y getTelefono");
        persona.setTelefono("5559876543");
        assertEquals("5559876543", persona.getTelefono());
    }

    /**
     * Test del getter y setter de zona
     */
    @Test
    public void testSetGetZona() {
        System.out.println("Prueba de setZona y getZona");
        persona.setZona("Zona Sur");
        assertEquals("Zona Sur", persona.getZona());
    }

}

