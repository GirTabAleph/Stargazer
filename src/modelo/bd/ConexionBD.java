package modelo.bd;

import java.sql.*;
/**
 *
 * @author Diego Ortega
 */
public class ConexionBD {
    
    //Esto trabajará como singleton.
    private static Connection conexion = null;
    
    private final String url = "jdbc:mysql://localhost:3306/ventas";
    private final String usuario = "root";
    private final String contrasenia = "fca.00";
    
    private ConexionBD(){
        
        //Las conexiones deben estár protegidas.
        try{
            
            conexion = DriverManager.getConnection(url, usuario, contrasenia);
            
        } catch(SQLException sqlex){
            
            sqlex.getStackTrace();
            
        }
        
    }
    
    public static Connection getInstance(){
        
        if(conexion == null){
            
            new ConexionBD();
            
        }
        
        return conexion;
        
    }
    
    public static void main(String args[]){
        
        Connection conexion = ConexionBD.getInstance();
        
    }
    
}
