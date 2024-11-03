package modelo;

//Esta es una DTO.

public class Persona {
    private int idPersona;
    private String nombre;
    private String rfc;
    private String domicilio;
    private String telefono;
    private int idZona;
    

    // Constructor
    protected Persona(int idPersona, String nombre, String rfc, String domicilio, String telefono, int idZona) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.rfc = rfc;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.idZona = idZona;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setZona(int idZona) {
        this.idZona = idZona;
    }


    //Getters
    
    public int getIdPersona(){
        return idPersona;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getIdZona() {
        return idZona;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", rfc=" + rfc + ", domicilio=" + domicilio + ", telefono=" + telefono + ", idZona=" + idZona + '}';
    }
    
    

}
