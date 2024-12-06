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

    public boolean insertarVenta(Venta venta) {
        
        /*
            public Venta(Date fechaVenta, int idZona, int idCliente, 
        int idVendedor, boolean requiereFactura, 
        List<ProductoVendido> productosV) {

        */
        boolean correcto = false;

        String insercionVenta = """
                                    INSERT INTO venta(
                                    	                                    	
                                    	fechaVenta,
                                    	idCliente,
                                    	idVendedor,
                                    	requiereFactura
                                        
                                    ) VALUES (
                                    	
                                    	?,
                                    	?,
                                    	?,
                                    	?
	
                                    )
                                    
                                    """;
        
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
        
        ResultSet resultadoVenta = null;
        
        int idVenta;
        
        try(PreparedStatement instruccionPrepVenta
                = conexion.prepareStatement(insercionVenta, Statement.RETURN_GENERATED_KEYS)){
            
            instruccionPrepVenta.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()) );
            instruccionPrepVenta.setInt(2, venta.getIdCliente());
            instruccionPrepVenta.setInt(3, venta.getIdVendedor());
            instruccionPrepVenta.setBoolean(4, venta.getRequiereFactura());
            
            conexion.setAutoCommit(false);
            
            if(instruccionPrepVenta.executeUpdate() == 1){

                resultadoVenta = instruccionPrepVenta.getGeneratedKeys();
                
                if(resultadoVenta.next()){
                    
                    idVenta = resultadoVenta.getInt(1);
                    
                    List<ProductoVendido> listaProductos = venta.getProductosV();
                                    
                    try(PreparedStatement instruccionPrepProducto
                            = conexion.prepareStatement(insercionProducto) ){

                        for(ProductoVendido producto: listaProductos){

                            instruccionPrepProducto.setInt(1, idVenta); //Variable generada
                            instruccionPrepProducto.setInt(2, producto.getIdProducto());
                            instruccionPrepProducto.setInt(3, producto.getCantidad());
                            instruccionPrepProducto.setDouble(4, producto.getPrecio());
                            instruccionPrepProducto.setDouble(5, producto.getDescuento());

                            instruccionPrepProducto.executeUpdate();
                            
                       
                        } //For each 
                        
                        conexion.commit();
                        correcto = true;

                    }catch(SQLException sqlex){
                        
                        System.out.println("No se guardó");
                    }
                
                }
            
            }
                   
 
        } catch(SQLException sqlex){
            
            System.out.println("Error al insertar venta.");
            try{   
                
                conexion.rollback();
            
            } catch(SQLException sqlex2){
                
            }    
        }

        return correcto;
        
    }
    
    //Sobre este no estoy seguro.
    @Override
    public boolean cancelarVenta(int idVenta, java.util.Date fechaCancelacion, String motivoCancel) throws IDNotFoundException {
        
        boolean correcto = true;
        String instruccion = """
                             UPDATE venta
                             SET cancelada = TRUE,
                                fechaCancelacion = ?,
                                motivoCancelacion = ?
                             WHERE idVenta = ?
                             """;
        try(PreparedStatement instruccionPreparada
                = conexion.prepareStatement(instruccion)){
            
            conexion.setAutoCommit(false);
            instruccionPreparada.setDate(1, new java.sql.Date(fechaCancelacion.getTime())); //Error aquí
            instruccionPreparada.setString(2, motivoCancel);
            instruccionPreparada.setInt(3, idVenta);
            
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
    public Venta getVenta(int idVenta) throws IDNotFoundException{ //Regresa el objeto
        
        String instruccion = """
                             SELECT * 
                             FROM venta
                             WHERE idVenta = ?
                             """
                             ;
        
        String instruccionLista = """
                                  SELECT * 
                                  FROM productoVendido
                                  WHERE idVenta = ?
                                  """
                                  ;
        
        List<ProductoVendido> listaProductos = new ArrayList<ProductoVendido>();
        
        Venta ventaRegresar = null;
        
        try(PreparedStatement instruccionPreparada = 
                conexion.prepareStatement(instruccion)){
            
            //Sin commit, no se modifica nada.
            instruccionPreparada.setInt(1, idVenta); //1era interrogante, id.
            
            try(ResultSet conjuntoResultados = instruccionPreparada.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    /*
                    int idVenta, Date fechaVenta, int idZona, int idCliente, int idVendedor, 
                    boolean requiereFactura, List<ProductoVendido> productosV
                    */

                    try(PreparedStatement instruccionPrepLista = 
                            conexion.prepareStatement(instruccionLista) ){
                        
                        //Llenado de la tabla.
                        instruccionPrepLista.setInt(1, idVenta);
                        try(ResultSet resultadoLista = instruccionPrepLista.executeQuery() ){

                            ProductoVendido productoAgregar = new ProductoVendido();
                            
                            while(resultadoLista.next() ){
                                
                                //No se carga el ID de la venta.
                                 productoAgregar.setIdProducto(resultadoLista.getInt("idProducto"));
                                 productoAgregar.setPrecio(resultadoLista.getDouble("precio"));
                                 productoAgregar.setCantidad(resultadoLista.getInt("cantidad"));
                                 productoAgregar.setDescuento(resultadoLista.getDouble("descuento"));
                                 
                                 listaProductos.add(productoAgregar);
                                
                            }
                            //Venta a regresar para el método.
                            ventaRegresar = new Venta(
                                conjuntoResultados.getInt("idVenta"),
                                conjuntoResultados.getDate("fechaVenta"),
                                conjuntoResultados.getInt("idCliente"),
                                conjuntoResultados.getInt("idVendedor"),
                                conjuntoResultados.getBoolean("requiereFactura"),
                                listaProductos    
                            );
                            
                        } catch(SQLException sqlex){
                            
                            System.out.println("Dentro del try interno algo salió mal.");
                            
                        }
                        
                    }catch(SQLException sqlex){
                        
                        System.out.println("Me lleva el chanfle, algo salió mal.");
                        
                    }
                    
                } else {
                    
                    throw new IDNotFoundException(idVenta);
                    
                }
                
            } catch (SQLException sqlex){ //Corresponde a ResultSet
                
                sqlex.printStackTrace();
                
            }
            
            
        } catch(SQLException sqlex){ //Corresponde a PreparedStatement
            
            sqlex.printStackTrace();
            
        }

        return ventaRegresar;
        
    }
    
    public static void main(String args[]) throws IDNotFoundException{
        
        DAOVentaBD dao = new DAOVentaBD();
        
        List <ProductoVendido> lista = new ArrayList<ProductoVendido>();
        lista.add(new ProductoVendido(1, 1.0, 1, 1.0) );
        lista.add(new ProductoVendido(2, 2.0, 2, 2.0) );

        if(dao.insertarVenta(new Venta(1, new java.util.Date(), 1, 1, false, lista) )){
            
            System.out.println("Ya inserté :D");
            
            if(dao.cancelarVenta(1, new java.util.Date(), "Porque sí")){
            
                System.out.println("Cancelada.");
            
            } else {

                System.out.println("Error de cancelación.");

            }

            Venta venta = dao.getVenta(1);
            System.out.println(venta.toString());
             
        } else {
            
            System.out.println("Valió madres.");
            
        }
        
    }
    
}
/*
Falta en el DAO regresar todas las ventas canceladas, las canceldasasn't,
ventas por cliente, ventas por producto, ventas por rangos de fecha

Cuando se especifique facturar (botón de la IGU) debe pedir los datos de la factura

ID de cliente solo se pediría cuando sea factura, si ID de cliente no existe permite añadir cliente 
(botón crear cliente)
*/
