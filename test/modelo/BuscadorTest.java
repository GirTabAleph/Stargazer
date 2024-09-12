/**
 *
 * @author Diego Ortega
 * @desc Prueba unitaria para la clase Buscador.
 */

package modelo;

import exceptions.IDNotFoundException;
import modelo.*;
//Import de los métodos porque o yo soy idiota y no sé usar los paquetes
//o NetBeans me odia.
import static modelo.Buscador.buscarClientePorId;
import static modelo.Buscador.buscarProductoPorId;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuscadorTest {
    
    //Atributos para las pruebas.
    
    //Nota: Todas estas pruebas toman un arreglo de la clase correspondiente
    //de un solo elemento.
    private Producto[] productos = new Producto[1];
    private Cliente[] clientes = new Cliente[1];
    private Vendedor[] vendedores = new Vendedor[1];
    
    
    public BuscadorTest() {
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
     * Test of buscarProductoPorId method, of class Buscador.
     */
    @Test(expected = IDNotFoundException.class)
    public void testBuscarProductoPorId() throws IDNotFoundException{
        //Por algún motivo desconocido para mi, la prueba funciona añadieno
        //el throws a la misma prueba.
        
        System.out.println("Prueba de buscarProductoPorId");
        //Probar con un prodcuto en el arreglo.
        productos[0] = new Producto();
        productos[0].setId(111);
        
        //En teoría, la execpción se lanza aquí.
        Producto res = buscarProductoPorId(productos, 0); //0 como ID inexistente.
        assertEquals(res, null);
        System.out.println("Excepción capturada :D");
                       
    }

    /**
     * Test of buscarClientePorId method, of class Buscador.
     */
    
    @Test (expected = IDNotFoundException.class)
    public void testBuscarClientePorId() throws IDNotFoundException{
        
        System.out.println("Prueba de buscarClientePorId");
        clientes[0] = new Cliente("Daniel Silva Rico", "VECJ880326", 
                "606 Bedford avenue", "5543153174", "Chinconcuac");
        
        clientes[0].setIdCliente(111);
    
        //En teoría, la execpción se lanza aquí.
        Cliente resCliente = buscarClientePorId(clientes, 0); //0 como ID inexistente.
        assertEquals(resCliente, null);
        System.out.println("Excepción cachada :D");
        
    }
    
    
    /**
     * Test of buscarVendedorPorId method, of class Buscador.
     */
    
    @Test(expected = IDNotFoundException.class)
    public void testBuscarVendedorPorId() throws IDNotFoundException {
        System.out.println("Prueba de buscarVendedorPorId");
        vendedores[0] = new Vendedor("Person McPersony", "0123456789",
                 "Toluca 8", "5538779463", "Tlaxcala");
        
        vendedores[0].setIdVendedor(111);
        
        Vendedor resVendedor = Buscador.buscarVendedorPorId(vendedores, 0);
        assertEquals(resVendedor, null);
        System.out.println("Yadda yadda excepción recibida :| ");
        
    }
    
    
}
