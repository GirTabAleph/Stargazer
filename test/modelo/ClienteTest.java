package modelo;

import exceptions.InvalidDateFormatException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego Ortega
 */
public class ClienteTest {
    
    //Atributos para la prueba.
    
    /*
    public Cliente(String nombre, String rfc, String domicilio, String telefono, String zona)
    */
    private Cliente pruebaCliente = new Cliente("Nacho Varga", "1234567890", 
            "Mishnory Commensality", "5538779463", "Nogales", "2024/02/02", 111);
    
    
    
    public ClienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of setFechaPrimeraCompra method, of class Cliente.
     */
    
    
    
    /**
     * Test of getFechaPrimeraCompra method, of class Cliente.
     */
    
    
    @Test
    public void testGetFechaPrimeraCompra(){
                    
            System.out.println("Prueba de getFechaPrimeraCompra");
            assertEquals("2024/02/02", pruebaCliente.getFechaPrimeraCompra());

        
        
    }
    
    
    /**
     * Test of setIdCliente method, of class Cliente.
     */
    
    @Test
    public void testSetIdCliente() {
        System.out.println("Prueba de setIdCliente");
        assertEquals(111, pruebaCliente.getIdCliente());
    }
    
   
    /**
     * Test of getIdCliente method, of class Cliente.
     */
    
    @Test
    public void testGetIdCliente() {
        System.out.println("Prueba de getIdCliente");
        assertEquals(111, pruebaCliente.getIdCliente());
        
    }
    
    
    /**
     * Test of crearCliente method, of class Cliente.
     */
    
    
    @Test
    public void testCrearCliente() {
        System.out.println("Prueba de crearCliente");
        Cliente pruebaDos = Cliente.crearCliente();
    }
    
    @Test(expected = InvalidDateFormatException.class)
    public void testSetFechaPrimeraCompra() throws InvalidDateFormatException{
        
        System.out.println("Prueba de setFechaPrimeraCompra");

        // Intentar asignar una fecha con formato incorrecto (debería lanzar InvalidDateFormatException)
        pruebaCliente.setFechaPrimeraCompra("2024/09/04");
        assertEquals("2024/09/04", pruebaCliente.getFechaPrimeraCompra());

    }
    
}
