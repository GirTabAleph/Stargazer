/*

package iteratorPrueba;

import java.util.List;
import modelo.Producto;

public class ProductoListIterator implements IProductoIterator {
    
    private List<Producto> productos;
    private int posicion;

    public ProductoListIterator(List<Producto> productos) {
        
        this.productos = productos;
        this.posicion = 0;
        
    }

    @Override
    public boolean hasNext() {
        
        return posicion < productos.size();
        
    }

    @Override
    public Producto next() {
        
        if (!hasNext()) {
            
            return null;
            
        }
        
        return productos.get(posicion++);
        
    }
    
}

*/
