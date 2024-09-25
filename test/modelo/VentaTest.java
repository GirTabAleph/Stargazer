package modelo;

/**
 *
 * @author Israel
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

public class VentaTest {

    private Venta venta;
    private Producto producto;
    private Cliente cliente;
    private Vendedor vendedor;

    @Before
    public void setUp() {
        // Inicializa los objetos necesarios para las pruebas
        producto = new Producto(1, "Laptop", "Almacén A", 1500.0, 1200.0, 10.0, 
                "Electrónica","90", 5, 100, 50);
        cliente = new Cliente("Cliente1", "RFC123", "Domicilio1", 
                "555-1234", "Zona1");
        vendedor = new Vendedor("Vendedor1", "RFC456", "Domicilio2",
                "555-5678", "Zona2", "2024-01-01", 
                10.0, 1);
        venta = new Venta(producto, producto.getPrecio(), producto.getPrecio() * 2,
                LocalDateTime.now(), "Zona1", cliente, vendedor, true);
    }

    @Test
    public void testCrearVenta() {
        Venta nuevaVenta = Venta.crearVenta(producto, 2, LocalDateTime.now(), "Zona1", cliente, vendedor, true);
        assertNotNull(nuevaVenta);
        assertEquals(producto, nuevaVenta.getProducto());
        assertEquals(2 * producto.getPrecio(), nuevaVenta.getCostoTotal(), 0.001);
        assertTrue(nuevaVenta.requiereFactura());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearVentaConProductoNulo() {
        Venta.crearVenta(null, 2, LocalDateTime.now(), "Zona1", cliente, vendedor, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearVentaConClienteNulo() {
        Venta.crearVenta(producto, 2, LocalDateTime.now(), "Zona1", null, vendedor, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearVentaConVendedorNulo() {
        Venta.crearVenta(producto, 2, LocalDateTime.now(), "Zona1", cliente, null, true);
    }

    @Test
    public void testGetters() {
        assertEquals(producto, venta.getProducto());
        assertEquals(cliente, venta.getCliente());
        assertNotNull(venta.getFechaVenta());
        assertEquals("Zona1", venta.getZona());
        assertEquals(2 * producto.getPrecio(), venta.getCostoTotal(), 0.001);
    }

    @Test
    public void testEqualsAndHashCode() {
        Venta otraVenta = new Venta(producto, producto.getPrecio(), producto.getPrecio() * 2, venta.getFechaVenta(), "Zona1", cliente, vendedor, true);
        assertEquals(venta, otraVenta);
        assertEquals(venta.hashCode(), otraVenta.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Venta{" +
                "producto=" + producto +
                ", costoUnitario=" + producto.getPrecio() +
                ", costoTotal=" + 2 * producto.getPrecio() +
                ", fechaVenta=" + venta.getFechaVenta() +
                ", zona='Zona1'" +
                ", cliente=" + cliente +
                ", vendedor=" + vendedor +
                ", requiereFactura=true" +
                '}';
        assertEquals(expectedString, venta.toString());
    }
}
