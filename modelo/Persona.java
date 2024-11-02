/*

package modelo;

//Esta es una DTO.

public class Persona {
   
    private String nombre;
    private String rfc;
    private String domicilio;
    private String telefono;
    private Zona zona;
    private TipoPersona tipo;

    // Constructor
    public Persona(String nombre, String rfc, String domicilio, String telefono, Zona zona) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.zona = zona;
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

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public void setTipo(TipoPersona tipo) {
        this.tipo = tipo;
    }
    
    


    //Getters

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

    public TipoPersona getTipo() {
        return tipo;
    }

    public Zona getZona() {
        return zona;
    }
    
    
}

*/

package modelo;

//Esta es una DTO.

public class Persona {
    
    private int idPersona;
    private String nombre;
    private String rfc;
    private String domicilio;
    private String telefono;
    private Zona zona;
    private TipoPersona tipo;

    // Constructor
    protected Persona(String nombre, String rfc, String domicilio, String telefono, Zona zona) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.zona = zona;
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

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public void setTipo(TipoPersona tipo) {
        this.tipo = tipo;
    }
    
    


    //Getters

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

    public TipoPersona getTipo() {
        return tipo;
    }

    public Zona getZona() {
        return zona;
    }

    public int getIdPersona() {
        return idPersona;
    }
    
    
    
    
}
