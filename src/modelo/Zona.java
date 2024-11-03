package modelo;

/**
 *
 * @author Iti
 */
public class Zona {
    
    private int idZona;
    private String nombre;
    private String descripcion;

    public Zona(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getIdZona(){
        return idZona;
    }
    
}
