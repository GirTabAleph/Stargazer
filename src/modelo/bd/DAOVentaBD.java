package modelo.bd;

import exceptions.IDNotFoundException;
import exceptions.ProductIDNotFoundException;
import java.sql.*;
import java.util.*;
import modelo.IDAOVenta;
import modelo.NuevaVenta;
import modelo.Producto;
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
                                    	requiereFactura
                                    
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

                            

                            instruccionPrepProducto.setInt(1, idVenta); //Variable generada
                            instruccionPrepProducto.setInt(2, producto.getIdProducto());
                            instruccionPrepProducto.setInt(3, producto.getCantidad());
                            instruccionPrepProducto.setDouble(4, producto.getPrecio());
                            instruccionPrepProducto.setDouble(5, producto.getDescuento());

                        } //For each 

                        if(instruccionPrepProducto.executeUpdate() == 1){
                        
                            conexion.commit();
                            
                
                        } else {

                            conexion.rollback();
                            
                            correcto = false;
                        }
                    }catch(SQLException e2){
                        
                        System.out.println("No se guardo");
                    }
                }
            
            }
                
            
 
        } catch(SQLException sqlex){
            
            System.out.println("Me voy a pegar un tiro, algo salio mal.");
            
        }
        
        
        
        
        
        return correcto;
        
    }
    
    //Sobre este no estoy seguro.
    @Override
    public boolean cancelarVenta(int idVenta) throws IDNotFoundException {
        
        boolean correcto = true;
        String instruccion = """
                             UPDATE venta 
                             SET cancelada = true 
                             	WHERE idVenta = ?
                             
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
    public Venta getVenta(int idVenta) { //Regresa el objeto
        String instruccion = """
                             SELECT * 
                             FROM Venta
                             WHERE idVenta = ?
                             """
                             ;
        
        Venta venta = null;
        
        try(PreparedStatement instruccionPreparada = 
                conexion.prepareStatement(instruccion)){
            
            //Sin commit, no se modifica nada.
            instruccionPreparada.setInt(1, idVenta); //1era interrogante, id.
            
            try(ResultSet conjuntoResultados = instruccionPreparada.executeQuery()){
                
                if(conjuntoResultados.next()){
                    //(int idVenta, Date fechaVenta, int idZona, int idCliente, int idVendedor, boolean requiereFactura, 
                    //List<ProductoVendido> productosV) tabla producto vendido, se extrae del mismo id, un prepare statetmen al mismo
                    //como son varios renglones se hace lo de get alll why next y extraer cada tupla 
                    //crear una lista insertar en la lista, insertar en la lista y crear la venta con la lista
                    venta = new Venta(
                        conjuntoResultados.getInt("idVenta"),
                        conjuntoResultados.getDate("fechaVenta"),
                        conjuntoResultados.getInt("idZona"),
                        conjuntoResultados.getInt("idCliente"),
                        conjuntoResultados.getInt("idVendedor"),
                        conjuntoResultados.getBoolean("requiereFactura"),
                        conjuntoResultados.getBoolean("cancelada"));//modificar venta
                    
                } else {
                    
                    throw new ProductIDNotFoundException(id);
                    
                }
                
            } catch (SQLException sqlex){
                
                sqlex.printStackTrace();
                
            }
            
            
        } catch(SQLException sqlex){
            
            sqlex.printStackTrace();
            
        }
        
        
        
        return venta;
    }
    
    
    public static void main(String [] args)throws IDNotFoundException{
        
        DAOVentaBD dao = new DAOVentaBD();
        
        List<ProductoVendido> lista = new ArrayList<>(); 
        lista.add(new ProductoVendido(1,1.0, 1, 1.0));
        
        if(dao.insertarVenta(new Venta(1,new java.util.Date(), 1,
                1,1,false, lista))){
            
            System.out.println("Ya inserte");
        }else{
            
            System.out.println("error");
        }
        
        if(dao.cancelarVenta(1)){
            
            System.out.println("Cancelado");
        }else{
            
            System.out.println("Error");
        }
    }
 
    
    
}
/*
drop table productovendido cascade;
drop table venta;

 CREATE TABLE venta (
    
    idVenta INT PRIMARY KEY AUTO_INCREMENT,
    fechaVenta DATETIME NOT NULL,
    idZona INT NOT NULL,
    idCliente INT NOT NULL,
    idVendedor INT NOT NULL,
    requiereFactura BOOLEAN NOT NULL,
    cancelada BOOLEAN 

    );

CREATE TABLE productoVendido (

	idVenta INT NOT NULL, 
	idProducto INT NOT NULL REFERENCES venta(idProducto),
	cantidad INT NOT NULL,
	precio DECIMAL(5.2) NOT NULL,
	descuento decimal (5.2) NULL,

	PRIMARY KEY (idVenta, idProducto),
	FOREIGN KEY (idVenta) REFERENCES venta(idVenta)

);


*/
