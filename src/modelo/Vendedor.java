package modelo;


import java.util.Date;
import javax.swing.JOptionPane;

//Esta NO ES DTO. Tiene un método para crear vendedores
//y eso hace que ya no sea DTO.

public class Vendedor extends Persona {
    
    private Date fechaContratacion;
    private double porcentajeComisiones;

    // Constructor
    public Vendedor(int idPersona, String nombre, String rfc, String domicilio, String telefono, int idZona) {
        
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);

    }
    
    
    public Vendedor(int idPersona, String nombre, String rfc, String domicilio, String telefono, 
            int idZona, Date fechaContratacion, double porcentajeComisiones) {
        
        super(idPersona, nombre, rfc, domicilio, telefono, idZona);
        
        this.fechaContratacion = fechaContratacion;
        this.porcentajeComisiones = porcentajeComisiones;
        
    }

    //Setters

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public void setPorcentajeComisiones(double porcentajeComisiones) {
        this.porcentajeComisiones = porcentajeComisiones;
    }


    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public double getPorcentajeComisiones() {
        return porcentajeComisiones;
    }
    

    // Método para capturar datos del vendedor
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

    @Override
    public String toString() {
        return super.toString() + "Vendedor{" + "fechaContratacion=" + fechaContratacion + ", porcentajeComisiones=" + porcentajeComisiones + '}';
    }
    
    
}
