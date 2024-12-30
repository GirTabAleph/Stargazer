//Modificado el 29 de diciembre de 2024.

package modelo;

import java.util.Date;
import javax.swing.JOptionPane;

//Pues me cago en Dios, ya se volvió DTO.

public class Vendedor extends Persona {
    
    private int idVendedor;
    private Date fechaContratacion;
    private double porcentajeComisiones;

    // Constructor
    public Vendedor(int idPersona, String nombre, String rfc, String domicilio, String telefono, int idZona) {
        
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);

    }
    
    
    public Vendedor(int idPersona, String nombre, String rfc, String domicilio, String telefono, 
            int idZona, Date fechaContratacion, double porcentajeComisiones, int idVendedor) {
        
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);
        
        this.fechaContratacion = fechaContratacion;
        this.porcentajeComisiones = porcentajeComisiones;
        this.idVendedor = idVendedor;
        
    }

    //Setters y getters.

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getPorcentajeComisiones() {
        return porcentajeComisiones;
    }

    public void setPorcentajeComisiones(double porcentajeComisiones) {
        this.porcentajeComisiones = porcentajeComisiones;
    }
    
    //No sirve, ignorar.
    /*
    public static Vendedor crearVendedor() {
        
        int idPersona = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el identificador (ID) del vendedor:"));
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del vendedor:");
        String rfc = JOptionPane.showInputDialog("Ingrese el RFC del vendedor:");
        String domicilio = JOptionPane.showInputDialog("Ingrese el domicilio del vendedor:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del vendedor:");
        int idZona = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la zona:"));
        Date fechaContratacion = null;//JOptionPane.showInputDialog("Ingrese la fecha de contratación del vendedor:");
        double porcentajeComisiones = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje de comisiones del vendedor:"));
        

        return new Vendedor(idPersona, nombre, rfc, domicilio, telefono, idZona, fechaContratacion, porcentajeComisiones);
    }
    */

    @Override
    public String toString() {
        return super.toString() + "Vendedor{" + "fechaContratacion=" + fechaContratacion + ", porcentajeComisiones=" + porcentajeComisiones + '}';
    }
    
    
}
