//Nota: Hacerle su adapter a la interfaz para darle versatilidad.

package modelo.bd;

import exceptions.IDNotFoundException;
import java.sql.*;
import java.util.*;
import modelo.IDAOVenta;
import modelo.ProductoVendido;
import modelo.Venta;

/**
 *
 * @author Diego Ortega
 */
public class DAOVentaBD implements IDAOVenta{
    
    private Connection conexion;
    
    private DAOVentaBD(){
        
        this.conexion = ConexionBD.getInstance();
        
    }

    @Override
    public boolean insertarVenta(Venta venta) {
        
        /*
            public Venta(Date fechaVenta, int idZona, int idCliente, 
        int idVendedor, boolean requiereFactura, 
        List<ProductoVendido> productosV) {

        */
        boolean correcto = true;

        String insercionVenta = """
                                    INSERT INTO venta(
                                    	                                    	
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
                                    	?
                                    	
                                    )
                                    
                                    """;
        
        ResultSet resultadoVenta = null;
        int idVenta;
        
        try(PreparedStatement instruccionPrepVenta
                = conexion.prepareStatement(insercionVenta, Statement.RETURN_GENERATED_KEYS)){
            
            instruccionPrepVenta.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()) );
            instruccionPrepVenta.setInt(2, venta.getIdZona());
            instruccionPrepVenta.setInt(3, venta.getIdCliente());
            instruccionPrepVenta.setInt(4, venta.getIdVendedor());
            instruccionPrepVenta.setBoolean(5, venta.getRequiereFactura());
            
            conexion.setAutoCommit(false);
            
            if(instruccionPrepVenta.executeUpdate() == 1){
                
                resultadoVenta = instruccionPrepVenta.getGeneratedKeys();
                
                if(resultadoVenta.next()){
                    
                    idVenta = resultadoVenta.getInt(1);
                    List<ProductoVendido> listaProductos = venta.getProductosV();
        
                    String insercionProducto = """
                                   INSERT INTO productoVendido (
                                   
                                        idVenta,
                                        idProducto,
                                        cantidad,
                                        precio,
                                        descuento
                                   
                                   ) VALUES (
                                        
                                        ?,
                                        ?,
                                        ?,
                                        ?,
                                        ?
                                   
                                   )
                                   
                                   """;
                                    
                    try(PreparedStatement instruccionPrepProducto
                            = conexion.prepareStatement(insercionProducto) ){

                        for(ProductoVendido producto: listaProductos){

                            /*
                            idVenta,
                                                    idProducto,
                                                    cantidad,
                                                    precio,
                                                    descuento
                            */

                            instruccionPrepProducto.setInt(1, idVenta); //Variable generada
                            instruccionPrepProducto.setInt(2, producto.getIdProducto());
                            instruccionPrepProducto.setInt(3, producto.getCantidad());
                            instruccionPrepProducto.setDouble(4, producto.getPrecio());
                            instruccionPrepProducto.setDouble(5, producto.getDescuento());

                        } //For each


                    }
            
                }

                //ESTO ES LO ÚLTIMO A HACER LPM.
                conexion.commit();

                
            } else {
                
                conexion.rollback();
                
            }
            
 
        } catch(SQLException sqlex){
            
            System.out.println("Me voy a pegar un tiro, algo salio mal.");
            
        }
        
        
        
        //Try con recursos.
        try(PreparedStatement instruccionPreparada 
                = conexion.prepareStatement(insercionVenta)){
            
            conexion.setAutoCommit(false);
           
            instruccionPreparada.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
            instruccionPreparada.setInt(2, venta.getIdZona());
            instruccionPreparada.setInt(3, venta.getIdCliente());
            instruccionPreparada.setInt(4, venta.getIdVendedor());
            instruccionPreparada.setBoolean(5, venta.getRequiereFactura());
            
            /*
            
            */
            
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
