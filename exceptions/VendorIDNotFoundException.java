package exceptions;

/**
 *
 * @author Diego Ortega
 */
public class VendorIDNotFoundException extends IDNotFoundException{
    
    public VendorIDNotFoundException(int id) {
        super(id);
    }
    
    @Override
    public String getMessage(){
        
        return "Error: ID de vendedor " + id + " no encontrado.";
        
    }
    
}
