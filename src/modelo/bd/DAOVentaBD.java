//Nota: Hacerle su adapter a la interfaz para darle versatilidad.

package modelo.bd;

import exceptions.IDNotFoundException;
import java.sql.*;
import modelo.IDAOVenta;
import modelo.Venta;

/**
 *
 * @author Diego Ortega
 */
public class DAOVentaBD implements IDAOVenta{
    
    private Connection conexion;
    
    public DAOVentaBD(){
        
        this.conexion = ConexionBD.getInstance();
        
    }

    @Override
    public boolean insertarVenta(Venta venta) {
        
        /*
        public Venta(int idProductoV, double costoUnitario, double costoTotal, 
            LocalDateTime fechaVenta, int idZona, int idCliente, int idVendedor,
            boolean requiereFactura) {
        */
        
        String instruccion = """
                                    INSERT INTO venta(
                                    	                                    	
                                    	idProductoV,
                                    	costoUnitario,
                                    	costoTotal,
                                    	fechaVenta,
                                    	idZona,
                                    	idCliente,
                                    	idVendedor,
                                    	requiereFactura,
                                    
                                    ) VALUES (
                                    	
                                    	
                                    	?,
                                    	?,
                                    	?,
                                    	?,
                                    	?,
                                    	?,
                                    	?
                                    	
                                    )
                                    
                                    """;
        boolean correcto = true;
        
        //Try con recursos.
        try(PreparedStatement instruccionPreparada 
                = conexion.prepareStatement(instruccion)){
            
            conexion.setAutoCommit(false);
           
            instruccionPreparada.setInt(1, venta.getIdProductoV());
            instruccionPreparada.setDouble(2, venta.getCostoUnitario());
            instruccionPreparada.setDouble(3, venta.getCostoTotal());
            instruccionPreparada.setDate(4, new Date(venta.getFechaVenta().getTime()));
            instruccionPreparada.setInt(5, venta.getIdZona());
            instruccionPreparada.setInt(6, venta.getIdCliente());
            instruccionPreparada.setInt(7, venta.getIdVendedor());
            instruccionPreparada.setBoolean(8, venta.getRequiereFactura());
            
            if(instruccionPreparada.executeUpdate() == 1){
                
                conexion.commit();
                //System.out.println("Insertado");
                
            } else {
                
                conexion.rollback();
                correcto = false;
                
            }
           
            
        } catch(SQLIntegrityConstraintViolationException sqlicve){
            
            System.out.println("Error: ID no es único.");
            correcto = false;
            
        } catch(SQLException sqlex){
            
            System.out.println("Error de SQL, sabrá dios que pasó.");
            sqlex.printStackTrace();
            correcto = false;
            
        }
        
        return correcto;
        
    }
    
    //Sobre este no estoy seguro.
    @Override
    public boolean cancelarVenta(int idVenta) throws IDNotFoundException {
        
        boolean correcto = true;
        String instruccion = """
                             DELETE FROM venta 
                             	WHERE id = ?
                             """;
        try(PreparedStatement instruccionPreparada
                = conexion.prepareStatement(instruccion)){
            
            conexion.setAutoCommit(false);
            instruccionPreparada.setInt(1, idVenta);
            
            if(instruccionPreparada.executeUpdate() == 1){
                
                conexion.commit();
                
            } else {
                
                conexion.rollback();
                correcto = false;
                
            }
            
        } catch(SQLException sqlex){
            
            System.out.println("Error: Algo salió mal, sepa Dios qué.");
            sqlex.printStackTrace();
            correcto = false;
            
        }
        
        return correcto;
        
    }

    @Override
    public int buscarVenta(int idVenta) { //Regresa posición, esto es 
        // en arraylist. No lo usaremos en BD. Cuando cree el adapter algún día 
        // esto ya no estará aquí.
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Venta getVenta(int idVenta) { //Regresa el objeto
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}