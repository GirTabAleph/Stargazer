package ModeloBD;

import ModeloBD.ConexionBD;
import exceptions.NameNotFoundException;
import exceptions.ProductIDNotFoundException;
import modelo.IDAOProducto;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;

/**
 *
 * @author Diego Ortega
 */
public class DAOProductoBD implements IDAOProducto {
    
    private Connection conexion;
    
    public DAOProductoBD(){
        
        this.conexion = ConexionBD.getInstance();
        
    }

    @Override
    public boolean agregarProducto(Producto producto) {
        
        String instruccion = """
                                    INSERT INTO producto(
                                    	
                                    	id,
                                    	nombre,
                                    	ubicacion,
                                    	precio,
                                    	costo,
                                    	descuento,
                                    	categoria,
                                    	proveedor,
                                    	stockMin,
                                    	stockMax,
                                    	existencias
                                    
                                    ) VALUES (
                                    	
                                    	?,
                                    	?,
                                    	?,
                                    	?,
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
        
        //Esto es un try with resources.
        //Nos va a evitar el bloque finally.
        try(PreparedStatement instruccionPreparada 
                = conexion.prepareStatement(instruccion)){
            
            conexion.setAutoCommit(false);
            
            instruccionPreparada.setInt(1, producto.getId());
            instruccionPreparada.setString(2, producto.getNombre());
            instruccionPreparada.setString(3, producto.getUbicacion());
            instruccionPreparada.setDouble(4, producto.getPrecio());
            instruccionPreparada.setDouble(5, producto.getCosto());
            instruccionPreparada.setDouble(6, producto.getDescuento());
            instruccionPreparada.setString(7, producto.getCategoria());
            instruccionPreparada.setInt(8, producto.getProveedor());
            instruccionPreparada.setInt(9, producto.getStockMin());
            instruccionPreparada.setInt(10, producto.getStockMax());
            instruccionPreparada.setInt(11, producto.getExistencias());
            
            if(instruccionPreparada.executeUpdate() == 1){
                
                conexion.commit();
                
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

    @Override
    public boolean borrarProducto(int id) throws ProductIDNotFoundException {
        
        boolean correcto = true;
        String instruccion = """
                             DELETE FROM producto 
                             	WHERE id = ?
                             """;
        try(PreparedStatement instruccionPreparada
                = conexion.prepareStatement(instruccion)){
            
            conexion.setAutoCommit(false);
            instruccionPreparada.setInt(1, id);
            
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
    public boolean modificarProducto(int id, Producto producto) throws ProductIDNotFoundException {
        
        boolean correcto = true;
        String instruccion = """
                             UPDATE producto
                             SET nombre = ?,
                                ubicacion = ?,
                                precio = ?,
                                costo = ?,
                                descuento = ?,
                                categoria = ?,
                                proveedor = ?,
                                stockMin = ?,
                                stockMax = ?,
                                existencias = ?
                             WHERE id = ?;
                             """;
        
        try(PreparedStatement instruccionPreparada = conexion.prepareStatement(instruccion)){//No usamos statement por seguridad
        
            conexion.setAutoCommit(false);
            
            
            instruccionPreparada.setString(1, producto.getNombre());
            instruccionPreparada.setString(2, producto.getUbicacion());
            instruccionPreparada.setDouble(3, producto.getPrecio());
            instruccionPreparada.setDouble(4, producto.getCosto());
            instruccionPreparada.setDouble(5, producto.getDescuento());
            instruccionPreparada.setString(6, producto.getCategoria());
            instruccionPreparada.setInt(7, producto.getProveedor());
            instruccionPreparada.setInt(8, producto.getStockMin());
            instruccionPreparada.setInt(9, producto.getStockMax());
            instruccionPreparada.setInt(10, producto.getExistencias());
            instruccionPreparada.setInt(11, id);
            
            if (instruccionPreparada.executeUpdate() == 1){
            
                conexion.commit();
            
            } else {
            
                conexion.rollback();
                correcto = false;
            
            } 
            
        }catch (SQLException sqlex){
            
            System.out.println("Error de Actualización");
            sqlex.printStackTrace();
            correcto = false;
            
        }
        
        return correcto;
        
    }

    @Override
    public Producto getProductoById(int id) throws ProductIDNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto getProductoByName(String nombre) throws NameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object[][] getAllProductos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String args[]){
        
        DAOProductoBD dao = new DAOProductoBD();
        Producto producto = new Producto(1, "Jabón", "Aquí", 10.50, 1.50,
                0.0, "Limpieza", 1, 1, 10, 5);
        //dao.agregarProducto(producto);

       /* try {
            dao.borrarProducto(1);
        } catch (ProductIDNotFoundException ex) {
            System.out.println("Error :c");
        }

        */
        Producto producto2 = new Producto(2, "Jabón 2", "Allá", 10.90, 1.90,
                0.0, "Limpieza", 2, 2, 11, 6);
       
       try {
            dao.modificarProducto(2, producto2);
        } catch (ProductIDNotFoundException ex) {
            System.out.println("Error :c");
        }
        
    }
    
}
