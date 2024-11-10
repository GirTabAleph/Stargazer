/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Iti
 */
public class Director {
    
    public void constructorVendedor (BuilderPersona persona){
    
        persona.setTipoPersona(TipoPersona.VENDEDOR);
        persona.setNombre("Javier");
    
    }
    
    
    public void constructorCliente (BuilderPersona persona){
    
        
        persona.setTipoPersona(TipoPersona.CLIENTE);
        persona.setNombre("Eliasib");
    
    }
    
}
