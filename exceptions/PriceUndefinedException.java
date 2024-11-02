package exceptions;

/**
 *
 * @author Diego Ortega
 * @desc Clase de excepción para precios no definidos.
 */
public class PriceUndefinedException extends Exception{
    
    public PriceUndefinedException(){
        
        super("Error: El precio no está definido.");
        
    }
    
    public PriceUndefinedException(String msj){
        
        super(msj);
        
    }
    
}
