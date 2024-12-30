//Modificado el 29 de diciembre 2024.

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

    public Cliente(int idPersona, String nombre, String rfc, String domicilio, String telefono, int idZona) {
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);
    }
    
    
    public Cliente(int idPersona, String nombre, String rfc, String domicilio, String telefono,
            int idZona, Date fechaPrimeraCompra, int idCliente) {
        
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);
        
        this.fechaPrimeraCompra = fechaPrimeraCompra;
        this.idCliente = idCliente;
        
    }
    
    
    //Esto no sirve, ignorar
    /*
    public static Cliente crearCliente() {
        
        int idPersona = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de persona del cliente "));
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String rfc = JOptionPane.showInputDialog("Ingrese el RFC del cliente:");
        String domicilio = JOptionPane.showInputDialog("Ingrese el domicilio del cliente:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
        int idZona = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la zona: "));
        Date fechaPrimeraCompra = null;//JOptionPane.showInputDialog("Ingrese la fecha de la primera compra del cliente:");
        int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente: "));

        return new Cliente(idPersona, nombre, rfc, domicilio, telefono, idZona, fechaPrimeraCompra, idCliente);
    }
    */

    public Date getFechaPrimeraCompra() {
        return fechaPrimeraCompra;
    }

    public void setFechaPrimeraCompra(Date fechaPrimeraCompra) {
        this.fechaPrimeraCompra = fechaPrimeraCompra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
