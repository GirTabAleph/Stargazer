/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Persona {
    private String nombre;
    private String rfc;
    private String domicilio;
    private String telefono;
    private String zona;

    // Constructor
    public Persona(String nombre, String rfc, String domicilio, String telefono, String zona) {
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

    public void setZona(String zona) {
        this.zona = zona;
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

    public String getZona() {
        return zona;
    }

}
