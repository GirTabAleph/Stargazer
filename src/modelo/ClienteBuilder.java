/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Iti
 */
public class ClienteBuilder implements BuilderPersona{
    
    private String rfc;
    private String domicilio;
    private String telefono;
    private Zona zona;
    private String nombre;
    private TipoPersona tipoPersona;
    private Date fechaPrimeraCompra;
    private int idCliente;
 

    @Override
    public void setNombre(String nombre) {
        
        this.nombre = nombre;
        
    }

    @Override
    public void setRfc(String rfc) {
        
        this.rfc = rfc;
    
    }

    @Override
    public void setDomicilio(String domicilio) {
        
        this.domicilio = domicilio;
    
    }

    @Override
    public void setTelefono(String telefono) {
        
        this.telefono = telefono;
    
    }

    @Override
    public void setZona(Zona zona) {
        
        this.zona = zona;
    
    }

    @Override
    public void setTipoPersona(TipoPersona tipoPersona) {
        
        this.tipoPersona = tipoPersona;
    
    }
    
    
    public void setFechaPrimeraCompra(Date fechaPrimeraCompra) {
        
        this.fechaPrimeraCompra = fechaPrimeraCompra;
    
    }
    
    public void setIdClientes(int idCliente) {
        
        this.idCliente = idCliente;
    
    }
    
    
    public Cliente getPersona() {
        
        return new Cliente (nombre, rfc, domicilio, telefono, zona, fechaPrimeraCompra, idCliente);

    
    }
    
    
    
}
