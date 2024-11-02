/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.JOptionPane;
import exceptions.InvalidDateFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente extends Persona {
    
    private Date fechaPrimeraCompra;
    private int idCliente;
    
    
    // Constructor

    public Cliente(String nombre, String rfc, String domicilio, String telefono, Zona zona) {
        super(nombre, rfc, domicilio, telefono, zona);
    }
    
    
    public Cliente(String nombre, String rfc, String domicilio, String telefono,
            Zona zona, Date fechaPrimeraCompra, int idCliente) {
        
        super(nombre, rfc, domicilio, telefono, zona);
        
        this.fechaPrimeraCompra = fechaPrimeraCompra;
        this.idCliente = idCliente;
    }

    // Getters y setters
    public Date getFechaPrimeraCompra() {
        return fechaPrimeraCompra;
    }
    
    public int getIdCliente(){
        return idCliente;
    }
    
    /*
     public void setFechaPrimeraCompra(String fechaPrimeraCompra) {
        this.fechaPrimeraCompra = fechaPrimeraCompra;
    }
    */
    //Posiblemente el peor setter que he modificado en mi vida.
    //Ponce, perdóneme por la aberración que va a leer.
    //-Atte. Diego Ortega.
    
    public void setFechaPrimeraCompra(Date fechaPrimeraCompra) throws InvalidDateFormatException{		
        
        this.fechaPrimeraCompra = fechaPrimeraCompra;

        //Este sdf es para validar el formato de fecha.
        
        
        /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd");
        sdf.setLenient(false); */
        
        //Intentar parsing de la fecha, si es válida se asigna.
       
        /*try{
            
            //sdf.parse(fechaPrimeraCompra);
            this.fechaPrimeraCompra = fechaPrimeraCompra;
            
        } catch (ParseException ex){
            
            //Cuando falle el parsing, tirar la excepción de fecha.
            throw new InvalidDateFormatException("Manín, el formato de fecha está mal.");
        } */
        
    
    }
    
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    // Método para capturar datos del cliente
    public static Cliente crearCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String rfc = JOptionPane.showInputDialog("Ingrese el RFC del cliente:");
        String domicilio = JOptionPane.showInputDialog("Ingrese el domicilio del cliente:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
        Zona zona = null; // JOptionPane.showInputDialog("Ingrese la zona del cliente:"); (Necesitamos 2 datos para crear una zona)
        Date fechaPrimeraCompra = null; //JOptionPane.showInputDialog("Ingrese la fecha de la primera compra del cliente:");
        int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el identificador (ID) del cliente:"));

        return new Cliente(nombre, rfc, domicilio, telefono, zona, fechaPrimeraCompra, idCliente);
    }

}
