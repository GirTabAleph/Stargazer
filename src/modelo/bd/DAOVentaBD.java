//Nota: Hacerle su adapter a la interfaz para darle versatilidad.
//Nota de la nota: creo que ya no, modificamos la interfaz y 
//ya no tiene esa bronca.

package modelo.bd;

import exceptions.IDNotFoundException;
import java.sql.*;
import java.util.*;
import modelo.IDAOVenta;
import modelo.ProductoVendido;
import modelo.Venta;
import exceptions.ClientIDNotFoundException;

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
    
    public Object[][] getCanceladas() {
    
        String instruccion = """
                             SELECT COUNT(*)
                             FROM venta
                             WHERE cancelada = 1
                             """;
        
        String instruccion2 = """
                             SELECT * 
                             FROM venta
                             WHERE cancelada = 1
                             """;
        
        Object[][] ventas = null;
        int totalTuplas;
        
        try(PreparedStatement instruccionPreparada 
                = conexion.prepareStatement(instruccion)){
            
            try(ResultSet conjuntoResultados = instruccionPreparada.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    totalTuplas = conjuntoResultados.getInt(1); //Obtenemos un solo resultado (el count).
                    ventas = new Object[totalTuplas][8];
                    
                    try(PreparedStatement instruccionPreparada2 
                            = conexion.prepareStatement(instruccion2)){
                        
                        try(ResultSet conjuntoResultados2 = instruccionPreparada2.executeQuery()){
                            
                            //Para llenar la matriz.
                            int renglon = 0;
                            while(conjuntoResultados2.next()){
                                
                                /*
                                int idVenta, Date fechaVenta, int idCliente, int idVendedor, 
                                boolean requiereFactura, List<ProductoVendido> productosV
                                */
                                
                                ventas[renglon][0] = conjuntoResultados2.getInt("idVenta");
                                ventas[renglon][1] = conjuntoResultados2.getDate("fechaVenta");
                                ventas[renglon][2] = conjuntoResultados2.getInt("idCliente");
                                ventas[renglon][3] = conjuntoResultados2.getInt("idVendedor");
                                ventas[renglon][4] = conjuntoResultados2.getBoolean("requiereFactura");
                                ventas[renglon][5] = conjuntoResultados2.getBoolean("cancelada");
                                ventas[renglon][6] = conjuntoResultados2.getDate("fechaCancelacion");
                                ventas[renglon][7] = conjuntoResultados2.getString("motivoCancelacion");
                                                                
                                renglon++;
                                
                            }
                            
                        } catch(SQLException sqlex){
                            
                            sqlex.printStackTrace();
                            
                        }
                        
                    } catch(SQLException sqlex){
                        
                        sqlex.printStackTrace();
                        
                    }
  
                } //LLAVE DE IF. NO BORRAR.
                
                
            } catch(SQLException sqlex){
                
                sqlex.printStackTrace();
                
            }
            
        } catch(SQLException sqlex){
            
            sqlex.printStackTrace();
            
        }
        
        return ventas;

    }
    
    //Muestra las no canceladas.
    public Object[][] getActivas() {
    
        String instruccion = """
                             SELECT COUNT(*)
                             FROM venta
                             WHERE cancelada != 1
                             """;
        
        String instruccion2 = """
                             SELECT * 
                             FROM venta
                             WHERE cancelada != 1
                             """;
        
        Object[][] ventas = null;
        int totalTuplas;
        
        try(PreparedStatement instruccionPreparada 
                = conexion.prepareStatement(instruccion)){
            
            try(ResultSet conjuntoResultados = instruccionPreparada.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    totalTuplas = conjuntoResultados.getInt(1); //Obtenemos un solo resultado (el count).
                    ventas = new Object[totalTuplas][8];
                    
                    try(PreparedStatement instruccionPreparada2 
                            = conexion.prepareStatement(instruccion2)){
                        
                        try(ResultSet conjuntoResultados2 = instruccionPreparada2.executeQuery()){
                            
                            //Para llenar la matriz.
                            int renglon = 0;
                            while(conjuntoResultados2.next()){
                                
                                /*
                                int idVenta, Date fechaVenta, int idCliente, int idVendedor, 
                                boolean requiereFactura
                                */
                                
                                ventas[renglon][0] = conjuntoResultados2.getInt("idVenta");
                                ventas[renglon][1] = conjuntoResultados2.getDate("fechaVenta");
                                ventas[renglon][2] = conjuntoResultados2.getInt("idCliente");
                                ventas[renglon][3] = conjuntoResultados2.getInt("idVendedor");
                                ventas[renglon][4] = conjuntoResultados2.getBoolean("requiereFactura");
                                ventas[renglon][5] = conjuntoResultados2.getBoolean("cancelada");
                                ventas[renglon][6] = conjuntoResultados2.getDate("fechaCancelacion");
                                ventas[renglon][7] = conjuntoResultados2.getString("motivoCancelacion");
                                                                
                                renglon++;
                                
                            }
                            
                        } catch(SQLException sqlex){
                            
                            sqlex.printStackTrace();
                            
                        }
                        
                    } catch(SQLException sqlex){
                        
                        sqlex.printStackTrace();
                        
                    }
  
                } //LLAVE DE IF. NO BORRAR.
                
                
            } catch(SQLException sqlex){
                
                sqlex.printStackTrace();
                
            }
            
        } catch(SQLException sqlex){
            
            sqlex.printStackTrace();
            
        }
        
        return ventas;

    }
    
    public Object[][] getVentasByClient(int idCliente) throws ClientIDNotFoundException{
        
        String instruccionSeleccion = """
                             SELECT *
                             FROM venta
                             WHERE idCliente = ?
                             """
                             ;
        
        String instruccionConteo = """
                             SELECT COUNT(*)
                             FROM venta
                             WHERE idCliente = ?
                             """;
        
        Object[][] ventasCliente = null;
        
        int totalTuplas;
        
        try(PreparedStatement preparadaConteo = 
                conexion.prepareStatement(instruccionConteo)){
       
            preparadaConteo.setInt(1, idCliente); //Única interrogante es id.
            
            try(ResultSet conjuntoResultados = preparadaConteo.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    totalTuplas = conjuntoResultados.getInt(1); //Obtenemos un solo resultado (el count).
                    ventasCliente = new Object[totalTuplas][8];
                    
                    try(PreparedStatement preparadaSeleccion 
                            = conexion.prepareStatement(instruccionSeleccion)){
                        
                        preparadaSeleccion.setInt(1, idCliente);
                         
                        try(ResultSet conjuntoResultados2 = preparadaSeleccion.executeQuery()){
                            
                            //Para llenar la matriz.
                            int renglon = 0;
                            while(conjuntoResultados2.next()){
                                
                                /*
                                int idVenta, Date fechaVenta, int idCliente, int idVendedor, 
                                boolean requiereFactura
                                */
                                
                                ventasCliente[renglon][0] = conjuntoResultados2.getInt("idVenta");
                                ventasCliente[renglon][1] = conjuntoResultados2.getDate("fechaVenta");
                                ventasCliente[renglon][2] = conjuntoResultados2.getInt("idCliente");
                                ventasCliente[renglon][3] = conjuntoResultados2.getInt("idVendedor");
                                ventasCliente[renglon][4] = conjuntoResultados2.getBoolean("requiereFactura");
                                ventasCliente[renglon][5] = conjuntoResultados2.getBoolean("cancelada");
                                ventasCliente[renglon][6] = conjuntoResultados2.getDate("fechaCancelacion");
                                ventasCliente[renglon][7] = conjuntoResultados2.getString("motivoCancelacion");
                                                                
                                renglon++;
                                
                            } //Llave while
                            
                        } catch(SQLException sqlex){
                            
                            System.out.println("Error ejecutando la selección");
                            sqlex.printStackTrace();
                            
                        }
                         
                     } catch(SQLException sqlex){
                         
                         System.out.println("Error preparando la selección");
                         sqlex.printStackTrace();
                         
                     }
                    
                } else {
                    
                    throw new ClientIDNotFoundException(idCliente);
                    
                } //Fin else 
                
            } catch(SQLException sqlex){
                
                System.out.println("Error ejecutando el conteo");
                sqlex.printStackTrace();
               
            }
        
        } catch(SQLException sqlex){
            
            System.out.println("Error preparando instrucción de conteo");
            sqlex.printStackTrace();
            
        }
        
        return ventasCliente;
        
    }
    
    //Recibe dos fechas como parámetro, no sé si son java.sql.Date o java.util.Date
    public Object[][] getByDateRange(java.sql.Date fechaInicio, java.sql.Date fechaFin){ 
        
        String instruccionSeleccion = """
                             SELECT *
                             FROM venta
                             WHERE fechaVenta BETWEEN ? AND ?
                             """
                             ;
        
        String instruccionConteo = """
                             SELECT COUNT(*)
                             FROM venta
                             WHERE fechaVenta BETWEEN ? AND ?
                             """;
        
        Object[][] ventasPorFechas = null;
        
        int totalTuplas;
        
        try(PreparedStatement preparadaConteo = 
                conexion.prepareStatement(instruccionConteo)){
       
            preparadaConteo.setDate(1, fechaInicio); //1era interrogante es la fecha de inicio.
            preparadaConteo.setDate(2, fechaFin); //2da interrogante es la fecha final.
            
            try(ResultSet conjuntoResultados = preparadaConteo.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    totalTuplas = conjuntoResultados.getInt(1); //Obtenemos un solo resultado (el count).
                    ventasPorFechas = new Object[totalTuplas][8];
                    
                    try(PreparedStatement preparadaSeleccion 
                            = conexion.prepareStatement(instruccionSeleccion)){
                        
                        preparadaSeleccion.setDate(1, fechaInicio);
                        preparadaSeleccion.setDate(2, fechaFin);
                         
                        try(ResultSet conjuntoResultados2 = preparadaSeleccion.executeQuery()){
                            
                            //Para llenar la matriz.
                            int renglon = 0;
                            while(conjuntoResultados2.next()){
                                
                                /*
                                int idVenta, Date fechaVenta, int idCliente, int idVendedor, 
                                boolean requiereFactura
                                */
                                
                                ventasPorFechas[renglon][0] = conjuntoResultados2.getInt("idVenta");
                                ventasPorFechas[renglon][1] = conjuntoResultados2.getDate("fechaVenta");
                                ventasPorFechas[renglon][2] = conjuntoResultados2.getInt("idCliente");
                                ventasPorFechas[renglon][3] = conjuntoResultados2.getInt("idVendedor");
                                ventasPorFechas[renglon][4] = conjuntoResultados2.getBoolean("requiereFactura");
                                ventasPorFechas[renglon][5] = conjuntoResultados2.getBoolean("cancelada");
                                ventasPorFechas[renglon][6] = conjuntoResultados2.getDate("fechaCancelacion");
                                ventasPorFechas[renglon][7] = conjuntoResultados2.getString("motivoCancelacion");
                                                                
                                renglon++;
                                
                            } //Llave while
                            
                        } catch(SQLException sqlex){
                            
                            System.out.println("Error ejecutando la selección");
                            sqlex.printStackTrace();
                            
                        }
                         
                     } catch(SQLException sqlex){
                         
                         System.out.println("Error preparando la selección");
                         sqlex.printStackTrace();
                         
                     }
                    
                } 
                
            } catch(SQLException sqlex){
                
                System.out.println("Error ejecutando el conteo");
                sqlex.printStackTrace();
               
            }
        
        } catch(SQLException sqlex){
            
            System.out.println("Error preparando instrucción de conteo");
            sqlex.printStackTrace();
            
        }
        
        return ventasPorFechas;
        
    }
   
    public static void main(String args[]) throws IDNotFoundException{
        
        DAOVentaBD dao = new DAOVentaBD();
        
        List <ProductoVendido> lista = new ArrayList<ProductoVendido>();
        lista.add(new ProductoVendido(1, 1.0, 1, 1.0) );
        lista.add(new ProductoVendido(2, 2.0, 2, 2.0) );
        lista.add(new ProductoVendido(3, 3.0, 3, 3.0) );
        
        /*
        
        if(dao.insertarVenta(new Venta(3, new java.util.Date(), 3, 3, false, lista) )){
            
            System.out.println("Ya inserté :D");
            
            if(dao.cancelarVenta(1, new java.util.Date(), "Porque es una prueba del DAO.")){
            
                System.out.println("Cancelada.");
            
            } else {

                System.out.println("Error de cancelación.");

            }

            Venta venta = dao.getVenta(1);
            System.out.println(venta.toString());
             
        } else {
            
            System.out.println("Valió madres.");
            
        }
        
        */
        
    }
    
}
/*
Diego at december 24, 2024, 12:38 says: Todos los get en teoría ya quedaron.

Cuando se especifique facturar (botón de la IGU) debe pedir los datos de la factura

ID de cliente solo se pediría cuando sea factura, si ID de cliente no existe permite añadir cliente 
(botón crear cliente)
*/
