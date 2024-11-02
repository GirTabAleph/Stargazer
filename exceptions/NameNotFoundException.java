/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author Diego Ortega
 */
public class NameNotFoundException extends Exception{
    
    private String nombre;
    
    public NameNotFoundException(String nombre){
        
        this.nombre = nombre;
        
        
    }
    
    @Override
    public String getMessage(){
        
        return "El nombre " + nombre + " no está definido";
        
    }
    
}
