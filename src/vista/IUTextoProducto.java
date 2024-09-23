package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Producto;

/**
 *
 * @author Diego Ortega
 */
public class IUTextoProducto implements IUProducto{
    
   private Scanner entrada = new Scanner(System.in);

    @Override
    public Producto leerProducto(int id) {
        
        System.out.print("Ingrese los siguientes datos del producto:\n");
        entrada.nextLine();
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = entrada.nextLine();
        System.out.print("Precio: ");
        double precio = entrada.nextDouble();
        System.out.print("Costo: ");
        double costo = entrada.nextDouble();
        System.out.print("Descuento: ");
        double descuento = entrada.nextDouble();
        System.out.print("Categoría: ");
        String categoria = entrada.nextLine();
        entrada.nextLine();
        System.out.print("ID del proveedor: ");
        int proveedor = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Stock mínimo: ");
        int stockMin = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Stock máximo: ");
        int stockMax = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Existencias: ");
        int existencias = entrada.nextInt();
        entrada.nextLine();
        
        return new Producto(id, nombre, ubicacion, precio, costo,
            descuento, categoria, proveedor, stockMin, stockMax, existencias);
    }

    @Override
    public void mostrarProducto(Producto producto) {
        
        System.out.println(producto);
        
    }

    @Override
    public int leerIdProducto(String texto) {
        
        //Texto define si se quiere borrar, actualizar, etc
        System.out.println("Ingrese el ID del producto a " + texto + ": ");
        
        return entrada.nextInt();
        
    } 

    @Override
    public String leerNombreProducto(String texto) {
        
        //Texto define si se quiere borrar, actualizar, etc
        System.out.println("Ingrese el nombre del producto a " + texto + ": ");
        
        return entrada.nextLine();
        
        
    }

    @Override
    public void mostrarListaProductos(List productos) {
        
        System.out.println("Listado de todos los productos.");
        System.out.println("ID   Nombre\t Ubicación\tPrecio\tCosto\tDescuento\tCategoría\tProveedor"
                + "\tStock mínimo\tStock máximo\tExistencias");
        
        for(Producto producto : (ArrayList<Producto>)productos){
            
            System.out.println(producto.getId() + " " 
                    + producto.getNombre() + " " 
                    + producto.getUbicacion() + " "
                    + producto.getPrecio() + " "
                    + producto.getCosto() + " "
                    + producto.getDescuento() + " "
                    + producto.getCategoria() + " "
                    + producto.getProveedor() + " "
                    + producto.getStockMin() + " "
                    + producto.getStockMax() + " "
                    + producto.getExistencias()
                    );
            
        }
        
    }
       
    @Override
    public int menuProductos() {
        
        System.out.print("Subsistema de productos\n\n1.Agregar un producto\n2.Borrar un producto"
                + "\n3.Modificar un producto\n4.Mostrar los productos\n0.Salir");
        System.out.print("\n\nSeleccione una opción: ");
        
        return entrada.nextInt();
        
    }

    @Override
    public void mensaje(String cadena) {
        
        System.out.println(cadena);
        
    }
    
}
