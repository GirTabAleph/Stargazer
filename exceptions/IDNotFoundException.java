package exceptions;

/**
 *
 * @author Diego Ortega
 * @desc Clase que contiene excepciones para ID no encontrado.
 */
public class IDNotFoundException extends Exception{
    
    protected int id;
    
    public IDNotFoundException(int id){
        
        super("Error: ID " + id + " no encontrado.");
        this.id = id;
        
    }
    
    public IDNotFoundException(String msj){
        
        super(msj);
        
    }
    
    
    
}
