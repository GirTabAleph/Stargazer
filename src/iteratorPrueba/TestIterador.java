/*

package iteratorPrueba;

import modelo.DAOProductoArrayList;
import modelo.Producto;


public class TestIterador {
    public static void main(String[] args) {
 
        DAOProductoArrayList daoProductos = new DAOProductoArrayList();


        daoProductos.agregarProducto(new Producto(1, "Leche", "A1", 20.0, 10.0, 0, "Lácteos", 1, 5, 50, 20));
        daoProductos.agregarProducto(new Producto(2, "Pan", "A2", 15.0, 7.0, 0, "Panadería", 2, 10, 100, 50));
        daoProductos.agregarProducto(new Producto(3, "Queso", "A3", 50.0, 30.0, 0, "Lácteos", 1, 5, 20, 10));


        IProductoIterator iterator = daoProductos.getIterator();

        System.out.println("Listado de productos:");
        
        while (iterator.hasNext()) {
            
            Producto producto = iterator.next();
            System.out.println(producto);
            
        }
        
    }
    
}

*/