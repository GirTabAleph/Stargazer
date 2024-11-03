package modelo;

import javax.swing.JOptionPane;
import exceptions.InvalidDateFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente extends Persona {
    
    private Date fechaPrimeraCompra;
    
    
    // Constructor

    public Cliente(int idPersona, String nombre, String rfc, String domicilio, String telefono, int idZona) {
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);
    }
    
    
    public Cliente(int idPersona, String nombre, String rfc, String domicilio, String telefono,
            int idZona, Date fechaPrimeraCompra) {
        
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);
        
        this.fechaPrimeraCompra = fechaPrimeraCompra;
        
    }

    // Getters y setters
    public Date getFechaPrimeraCompra() {
        return fechaPrimeraCompra;
    }
    
        
    /*
     public void setFechaPrimeraCompra(String fechaPrimeraCompra) {
        this.fechaPrimeraCompra = fechaPrimeraCompra;
    }
    */
    //Posiblemente el peor setter que he modificado en mi vida.
    //Ponce, perdóneme por la aberración que va a leer.
    //-Atte. Diego Ortega.
    
    public void setFechaPrimeraCompra(Date fechaPrimeraCompra){
        
       
        this.fechaPrimeraCompra = fechaPrimeraCompra;
            
    
    }
    
    
    // Método para capturar datos del cliente
    public static Cliente crearCliente() {
        
        int idPersona = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente: "));
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String rfc = JOptionPane.showInputDialog("Ingrese el RFC del cliente:");
        String domicilio = JOptionPane.showInputDialog("Ingrese el domicilio del cliente:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
        int idZona = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la zona: "));
        Date fechaPrimeraCompra = null;//JOptionPane.showInputDialog("Ingrese la fecha de la primera compra del cliente:");

        return new Cliente(idPersona, nombre, rfc, domicilio, telefono, idZona, fechaPrimeraCompra);
    }

}
