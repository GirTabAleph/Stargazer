/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ClienteFactory implements PersonaFactory{

    @Override
    public Persona crearPersona(int idPersona, String nombre, String rfc, String telefono, String domicilio, Zona zona, Date fecha, double porcentaje){
    
        Calendar calendario = Calendar.getInstance();
        return new Persona = Cliente (12, "Juan", "AAA123AAA123A", "5555555555", "Las lomas, Calle 13", "Tlaquepaque", calendario.getTime(), null);
    
    }
    
}
