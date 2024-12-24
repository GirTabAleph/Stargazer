package exceptions;

/**
 *
 * @author Diego Ortega
 */
public class ClientIDNotFoundException extends IDNotFoundException{
    
    public ClientIDNotFoundException(int id) {
        super(id);
    }
    
     @Override
    public String getMessage(){
        
        return "Error: ID de cliente " + id + " no encontrado.";
        
    }
    
}
