/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloBD;

import java.sql.*;

/**
 *
 * @author Iti
 */
public class ConexionBD {
    
    private static Connection conexion = null;
    
    private final String url = "jdbc:mysql://localhost:3306/ventas";
    private final String usuario = "root";
    private final String contrasenia = "fca.00";
    
    private ConexionBD(){
    
        try{
        
            conexion = DriverManager.getConnection(url, usuario, contrasenia);
            
        } catch(SQLException sqle){
        
            sqle.getStackTrace();
        
        }
    
    }
    
    public static Connection getInstance(){
    
        if(conexion == null){
        
            new ConexionBD();
        
        }
        
        return conexion;
    
    }
    
    public static void main(String[] args) {
        
        Connection conexion = ConexionBD.getInstance();
        
    }
    
}
