package modelo;

/**
 *
 * @author Israel
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendedorTest {

    private Vendedor vendedor;

    @Before
    public void setUp() {
 
        vendedor = new Vendedor("Juan Perez", "RFC123", "Av. Siempre Viva 123", 
                "5551234567", "Zona Norte", "2024-01-15"
                , 10.5, 1001);
    }

    @Test
    public void testConstructorHerencia() {
        // Verificamos que los atributos heredados de Persona se inicializan correctamente
        assertEquals("Juan Perez", vendedor.getNombre());
        assertEquals("RFC123", vendedor.getRfc());
        assertEquals("Av. Siempre Viva 123", vendedor.getDomicilio());
        assertEquals("5551234567", vendedor.getTelefono());
        assertEquals("Zona Norte", vendedor.getZona());
    }

    @Test
    public void testConstructorVendedor() {
        // Verificamos que los atributos de la clase Vendedor se inicializan correctamente
        assertEquals("2024-01-15", vendedor.getFechaContratacion());
        assertEquals(10.5, vendedor.getPorcentajeComisiones(), 0.001);
        assertEquals(1001, vendedor.getIdVendedor());
    }

    @Test
    public void testSetters() {
        // Modificamos los atributos mediante setters y verificamos que los valores se actualizan correctamente
        vendedor.setFechaContratacion("2025-02-20");
        vendedor.setPorcentajeComisiones(15.0);
        vendedor.setIdVendedor(2002);

        assertEquals("2025-02-20", vendedor.getFechaContratacion());
        assertEquals(15.0, vendedor.getPorcentajeComisiones(), 0.001);
        assertEquals(2002, vendedor.getIdVendedor());
    }

    @Test
    public void testSettersHerencia() {
        // Probamos los setters heredados de Persona
        vendedor.setNombre("Carlos Garcia");
        vendedor.setRfc("RFC456");
        vendedor.setDomicilio("Calle Falsa 123");
        vendedor.setTelefono("5559876543");
        vendedor.setZona("Zona Sur");

        assertEquals("Carlos Garcia", vendedor.getNombre());
        assertEquals("RFC456", vendedor.getRfc());
        assertEquals("Calle Falsa 123", vendedor.getDomicilio());
        assertEquals("5559876543", vendedor.getTelefono());
        assertEquals("Zona Sur", vendedor.getZona());
    }
}

