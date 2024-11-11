package modelo.bd;

import exceptions.IDNotFoundException;
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
                             
                             WHERE id = ?
                             """;
        try(PreparedStatement instruccionPreparada = 
                conexion.prepareStatement(instruccion)){
            
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

            if(instruccionPreparada.executeUpdate() == 1){
                
                conexion.commit();
                
            } else {
                
                conexion.rollback();
                correcto = false;
                
            }
            
        } catch(SQLException sqlex){
            
            System.out.println("Errorde actualización.");
            sqlex.printStackTrace();
            correcto = false;
            
        }
        
        return correcto;
        
    }

    @Override
    public Producto getProductoById(int id) throws ProductIDNotFoundException {
        
        String instruccion = """
                             SELECT * 
                             FROM producto
                             WHERE id = ?
                             """
                             ;
        
        Producto productoRegresar = null;
        
        try(PreparedStatement instruccionPreparada = 
                conexion.prepareStatement(instruccion)){
            
            //Sin commit, no se modifica nada.
            instruccionPreparada.setInt(1, id); //1era interrogante, id.
            
            try(ResultSet conjuntoResultados = instruccionPreparada.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    productoRegresar = new Producto(
                        conjuntoResultados.getInt("id"),
                        conjuntoResultados.getString("nombre"),
                        conjuntoResultados.getString("ubicacion"),
                        conjuntoResultados.getDouble("precio"),
                        conjuntoResultados.getDouble("costo"),
                        conjuntoResultados.getDouble("descuento"),
                        conjuntoResultados.getString("categoria"),
                        conjuntoResultados.getInt("proveedor"),
                        conjuntoResultados.getInt("stockMin"),
                        conjuntoResultados.getInt("stockMax"),
                        conjuntoResultados.getInt("existencias"));
                    
                } else {
                    
                    throw new ProductIDNotFoundException(id);
                    
                }
                
            } catch (SQLException sqlex){
                
                sqlex.printStackTrace();
                
            }
            
            
        } catch(SQLException sqlex){
            
            sqlex.printStackTrace();
            
        }
        
        return productoRegresar;
        
    }

    @Override
    public Producto getProductoByName(String nombre) throws NameNotFoundException {
        
        String instruccion = """
                             SELECT * 
                             FROM producto
                             WHERE nombre LIKE ?
                             """
                             ;
        
        Producto productoRegresar = null;
        
        try(PreparedStatement instruccionPreparada = 
                conexion.prepareStatement(instruccion)){
            
            //Sin commit, no se modifica nada.
            instruccionPreparada.setString(1, nombre); //1era interrogante, nombre.
            
            try(ResultSet conjuntoResultados = instruccionPreparada.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    productoRegresar = new Producto(
                        conjuntoResultados.getInt("id"),
                        conjuntoResultados.getString("nombre"),
                        conjuntoResultados.getString("ubicacion"),
                        conjuntoResultados.getDouble("precio"),
                        conjuntoResultados.getDouble("costo"),
                        conjuntoResultados.getDouble("descuento"),
                        conjuntoResultados.getString("categoria"),
                        conjuntoResultados.getInt("proveedor"),
                        conjuntoResultados.getInt("stockMin"),
                        conjuntoResultados.getInt("stockMax"),
                        conjuntoResultados.getInt("existencias"));
                    
                } else {
                    
                    throw new NameNotFoundException(nombre);
                    
                }
                
            } catch (SQLException sqlex){
                
                sqlex.printStackTrace();
                
            }
            
            
        } catch(SQLException sqlex){
            
            sqlex.printStackTrace();
            
        }
        
        return productoRegresar;
        
        
    }

    @Override
    public Object[][] getAllProductos() {
        
        String instruccion = """
                             SELECT COUNT(*)
                             FROM producto
                             """;
        
        String instruccion2 = """
                             SELECT * 
                             FROM producto
                             """;
        
        Object[][] productos = null;
        int totalTuplas;
        
        try(PreparedStatement instruccionPreparada 
                = conexion.prepareStatement(instruccion)){
            
            try(ResultSet conjuntoResultados = instruccionPreparada.executeQuery()){
                
                if(conjuntoResultados.next()){
                    
                    totalTuplas = conjuntoResultados.getInt(1); //Obtenemos un solo resultado (el count).
                    productos = new Object[totalTuplas][11];
                    
                    try(PreparedStatement instruccionPreparada2 
                            = conexion.prepareStatement(instruccion2)){
                        
                        try(ResultSet conjuntoResultados2 = instruccionPreparada2.executeQuery()){
                            
                            //Para llenar la matriz.
                            int renglon = 0;
                            while(conjuntoResultados2.next()){
                                
                                /*
                                public Producto(int id, String nombre, String ubicacion, double precio, double costo,
                                double descuento, String categoria, int proveedor, int stockMin,
                                int stockMax, int existencias) {
                                */
                                
                                productos[renglon][0] = conjuntoResultados2.getString("id");
                                productos[renglon][1] = conjuntoResultados2.getString("nombre");
                                productos[renglon][2] = conjuntoResultados2.getString("ubicacion");
                                productos[renglon][3] = conjuntoResultados2.getString("precio");
                                productos[renglon][4] = conjuntoResultados2.getString("costo");
                                productos[renglon][5] = conjuntoResultados2.getString("descuento");
                                productos[renglon][6] = conjuntoResultados2.getString("categoria");
                                productos[renglon][7] = conjuntoResultados2.getString("proveedor");
                                productos[renglon][8] = conjuntoResultados2.getString("stockMin");
                                productos[renglon][9] = conjuntoResultados2.getString("stockMax");
                                productos[renglon][10] = conjuntoResultados2.getString("existencias");

                                renglon++;
                                
                            }
                            
                        } catch(SQLException sqlex){
                            
                            sqlex.printStackTrace();
                            
                        }
                        
                    } catch(SQLException sqlex){
                        
                        sqlex.printStackTrace();
                        
                    }
  
                } //LLAVE DE IF. NO BORRAR LPM.
                
                
            } catch(SQLException sqlex){
                
                sqlex.printStackTrace();
                
            }
            
        } catch(SQLException sqlex){
            
            sqlex.printStackTrace();
            
        }
        
        return productos;
        
    }
    
    public static void main(String args[]){
        
        DAOProductoBD dao = new DAOProductoBD();
        Producto producto = new Producto(1, "Jabón", "Aquí", 10.50, 1.50,
                0.0, "Limpieza", 1, 1, 10, 5);
        //dao.agregarProducto(producto);
        
        /*
        try {
            dao.borrarProducto(1);
        } catch (ProductIDNotFoundException ex) {
            System.out.println("Error :c");
        }
        */
        
        /*
        try {
            
            Producto producto2 = dao.getProductoByName("Coco");
            System.out.println(producto2.toString());
            
        } catch (NameNotFoundException ex) {
            
            System.out.println("Nombre no encontrado.");
        
        }
        */
        Object[][] matriz = dao.getAllProductos();
        
        for(int i = 0; i < matriz.length; i++){
            
            for(int j = 0; j < matriz[i].length; j++){
              
                System.out.println(matriz[i][j]);
            
            }
            
        }
        
    } //main
    
}
