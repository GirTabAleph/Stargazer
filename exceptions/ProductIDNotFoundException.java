package exceptions;

/**
 *
 * @author Diego Ortega
 */
public class ProductIDNotFoundException extends IDNotFoundException{
    
    public ProductIDNotFoundException(int id) {
        super(id);
    }
    
    @Override
    public String getMessage(){
        
        return "Error: ID de producto " + id + " no encontrado.";
        
    }
    
}
