package modelo;

/**
 *
 * @author Israel
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ProductoVendidoTest {

    private ProductoVendido productoVendido;

    @Before
    public void setUp() {
        productoVendido = new ProductoVendido(1, 100.50f, 2, "Laptop");
    }

    @Test
    public void testGettersAndSetters() {
        // Prueba de los setters y getters de idProducto
        productoVendido.setIdProducto(2);
        assertEquals(2, productoVendido.getIdProducto());

        // Prueba de precio
        productoVendido.setPrecio(150.75f);
        assertEquals(150.75f, productoVendido.getPrecio(), 0.0f);

        // Prueba de cantidad
        productoVendido.setCantidad(3);
        assertEquals(3, productoVendido.getCantidad());

        // Prueba de nombre
        productoVendido.setNombre("Tablet");
        assertEquals("Tablet", productoVendido.getNombre());
        
        // Prueba de descuento
        productoVendido.setDescuento(10.0);
        assertEquals(10.0, productoVendido.getDescuento(), 0.0);
    }

    @Test
    public void testEqualsAndHashCode() {
        ProductoVendido productoVendido1 = new ProductoVendido(1, 100.50f, 2, "Laptop");
        ProductoVendido productoVendido2 = new ProductoVendido(1, 200.75f, 5, "Tablet");
        ProductoVendido productoVendido3 = new ProductoVendido(2, 100.50f, 2, "Laptop");

        // Verificar que dos productos con el mismo id sean iguales
        assertTrue(productoVendido1.equals(productoVendido2));
        assertEquals(productoVendido1.hashCode(), productoVendido2.hashCode());

        // Verificar que dos productos con diferente id no sean iguales
        assertFalse(productoVendido1.equals(productoVendido3));
    }

    @Test
    public void testToString() {
        String expected = "ProductoVendido{idProducto=1, precio=100.5, cantidad=2}";
        assertEquals(expected, productoVendido.toString());
    }
}
