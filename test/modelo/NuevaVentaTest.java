package modelo;

/**
 *
 * @author Israel
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

public class NuevaVentaTest {

    private NuevaVenta nuevaVenta;
    private Cliente cliente;
    private Vendedor vendedor;
    private IDAOProductoVendido productos;

    @Before
    public void setUp() {
     
        cliente = new Cliente("Cliente1", "RFC123", "Domicilio1",
                "555-1234", "Zona1");
        vendedor = new Vendedor("Vendedor1", "RFC456", "Domicilio2",
                "555-5678", "Zona2", "2024-01-01",
                10.0, 1);
        productos = new DAOProductoVendidoArrayList(); 
        nuevaVenta = new NuevaVenta(100.0, new Date(), "Zona1", 
                cliente, vendedor, true, productos);
    }

    @Test
    public void testGetters() {
        // Prueba de los métodos getter
        assertEquals(100.0, nuevaVenta.getPrecioTotal(), 0.001);
        assertNotNull(nuevaVenta.getFechaVenta());
        assertEquals("Zona1", nuevaVenta.getZona());
        assertEquals(cliente, nuevaVenta.getCliente());
        assertEquals(vendedor, nuevaVenta.getVendedor());
        assertTrue(nuevaVenta.isRequiereFactura());
    }

    @Test
    public void testSetters() {
        // Prueba de los métodos setter
        nuevaVenta.setPrecioTotal(200.0);
        assertEquals(200.0, nuevaVenta.getPrecioTotal(), 0.001);

        nuevaVenta.setZona("Zona2");
        assertEquals("Zona2", nuevaVenta.getZona());

        nuevaVenta.setRequiereFactura(false);
        assertFalse(nuevaVenta.isRequiereFactura());
    }

    @Test
    public void testEquals() {
        NuevaVenta otraVenta = new NuevaVenta(100.0, new Date(), 
                "Zona1", cliente, vendedor, true, productos);
        assertTrue(nuevaVenta.equals(otraVenta));

        otraVenta.setIdVenta(2); // Cambiar el id para hacerlos diferentes
        assertFalse(nuevaVenta.equals(otraVenta));
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = nuevaVenta.hashCode();
        NuevaVenta otraVenta = new NuevaVenta(100.0, new Date(), 
                "Zona1", cliente, vendedor, true, productos);
        assertEquals(expectedHashCode, otraVenta.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "NuevaVenta{" +
                "idVenta=0, productos=" + productos +
                ", precioTotal=100.0, fechaVenta=" + nuevaVenta.getFechaVenta() +
                ", zona=Zona1, cliente=" + cliente +
                ", vendedor=" + vendedor +
                ", requiereFactura=true" + '}';
        assertEquals(expectedString, nuevaVenta.toString());
    }
}
