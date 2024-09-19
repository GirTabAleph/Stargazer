package modelo;


import javax.swing.JOptionPane;

//Esta NO ES DTO. Tiene un método para crear vendedores
//y eso hace que ya no sea DTO.

public class Vendedor extends Persona {
    private String fechaContratacion;
    private double porcentajeComisiones;
    private int idVendedor;

    // Constructor

    public Vendedor(String nombre, String rfc, String domicilio, String telefono, String zona) {
        
        super(nombre, rfc, domicilio, telefono, zona);

    }
    
    
    public Vendedor(String nombre, String rfc, String domicilio, String telefono, 
            String zona, String fechaContratacion, double porcentajeComisiones, 
            int idVendedor) {
        
        super(nombre, rfc, domicilio, telefono, zona);
        
        this.fechaContratacion = fechaContratacion;
        this.porcentajeComisiones = porcentajeComisiones;
        this.idVendedor = idVendedor;
    }

    //Setters

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public void setPorcentajeComisiones(double porcentajeComisiones) {
        this.porcentajeComisiones = porcentajeComisiones;
    }
    
    public void setIdVendedor(int idVendedor){
        this.idVendedor = idVendedor;
    }


    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public double getPorcentajeComisiones() {
        return porcentajeComisiones;
    }
    
    public int getIdVendedor(){
        return idVendedor;
    }

    // Método para capturar datos del vendedor
    public static Vendedor crearVendedor() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del vendedor:");
        String rfc = JOptionPane.showInputDialog("Ingrese el RFC del vendedor:");
        String domicilio = JOptionPane.showInputDialog("Ingrese el domicilio del vendedor:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del vendedor:");
        String zona = JOptionPane.showInputDialog("Ingrese la zona de entrega del vendedor:");
        String fechaContratacion = JOptionPane.showInputDialog("Ingrese la fecha de contratación del vendedor:");
        double porcentajeComisiones = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje de comisiones del vendedor:"));
        int idVendedor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el identificador (ID) del vendedor:"));

        return new Vendedor(nombre, rfc, domicilio, telefono, zona, fechaContratacion, porcentajeComisiones, idVendedor);
    }
}

