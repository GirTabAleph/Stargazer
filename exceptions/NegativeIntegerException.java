package exceptions;

/**
 *
 * @author Diego Ortega
 */
public class NegativeIntegerException extends Exception{
    
    public NegativeIntegerException(){
        
        super("Error, el valor no puede ser negativo.");

    }
    
    @Override
    public String getMessage(){
        
        return "Error, el valor no puede ser negativo.";
        
    }
    
}
