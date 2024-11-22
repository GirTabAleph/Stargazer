package controlador;

import exceptions.ProductIDNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modelo.IDAOProducto;
import modelo.Producto;
import modelo.bd.DAOProductoBD;
import vista.IGUProductos;

/**
 * @author Israel
 */

public class ControlIGUProductos extends MouseAdapter implements ActionListener{
    
    private IGUProductos igu;
    //private IDAOProducto productos = new DAOProductoHashMap();
    private IDAOProducto productos = new DAOProductoBD();
    
    public ControlIGUProductos(IGUProductos igu){
        
        this.igu = igu;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        
        try {
            
            switch(comando){


                case "Aceptar" -> agregar();

                case "Eliminar" -> eliminar();

                case "Cancelar" -> cancelar();    

                case "Limpiar campos" -> limpiarCampos();

                case "Nuevo" -> nuevo();

                case "Borrar" -> borrar();

                case "Modificar" -> modificar();

                case "Buscar" -> buscar();
                    
                case "Editar" -> editar();
                
                case "Primero" -> primero();
                
                case "Anterior" -> anterior();
                
                case "Siguiente" -> siguiente();
                
                case "Ultimo" -> ultimo();

            }
        
        } catch (ProductIDNotFoundException ex) {
        
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void nuevo(){
    
        igu.cambiarAccion("Aceptar");
        igu.desactivarID();
        igu.desactivarToolbar();
        igu.activarCampos();
        igu.activarBotones();
        
        
    }
    
    public void borrar(){
        
        if(igu.getId() == 0){
        
            igu.mensajeError("Primero seleccione un producto");
        
        } else {

            igu.cambiarAccion("Eliminar");
            igu.activarID();
            igu.desactivarCampos();
            igu.activarBotones();
            igu.desactivarToolbar();
        }
        
    }
    
    public void editar(){
        
        if(igu.getId() == 0){
        
            igu.mensajeError("Primero seleccione un producto");
        
        } else {
        
        
            igu.cambiarAccion("Buscar");
            igu.activarID();
            igu.desactivarCampos();
            igu.activarBotones();
            igu.desactivarToolbar();

        }
        
    }
    
    public void buscar() throws ProductIDNotFoundException{
        
        Producto producto = productos.getProductoById(igu.getId());
        
        igu.setProducto(producto);
        igu.cambiarAccion("Modificar");
        
        igu.activarCampos();
        igu.desactivarID();
        igu.activarBotones();
        igu.desactivarToolbar();
    
    }
    
    public void modificar() throws ProductIDNotFoundException{
        
        if(productos.modificarProducto(igu.getId(), igu.getProducto(false))){
        
            igu.mensaje("Producto " + igu.getId() + " actualizado correctamente");
            igu.activarTabla();
                    
        } else {
        
            igu.mensajeError("El producto " + igu.getId() + "no pude ser actualizado");
        
        }
        
        igu.limpiarCampos();
        igu.desactivarEdicion();
        igu.activarToolbar();
        
    }
    
    public void agregar() {

        Producto producto = igu.getProducto();
        if (productos.agregarProducto(producto)) {

            //IUTextoProducto texto = new IUTextoProducto(); 
            igu.mensaje("Producto agregado correctamente." + producto);
            igu.activarTabla();
            
        } else {

            igu.mensajeError("No se ha podido agregar el producto " + producto);

        }
        
        igu.limpiarCampos();
        igu.desactivarEdicion();
        igu.activarToolbar();

    }
    
    public void eliminar() throws ProductIDNotFoundException{

        int borrar = igu.confirmacion("¿Seguro que desea borrar el producto con ID: " + igu.getId() + "?");
        
        if(borrar == 0){
            
            if(productos.borrarProducto(igu.getId())){
        
                igu.mensaje("Producto" + igu.getId() + " borrado correctamente");
                igu.activarTabla();
                
            } else {
            
                igu.mensajeError("No se pudo borrar");
            
            }
            
        } else {
        
            igu.mensajeError("El producto" + igu.getId() + " no se borro");
        
        }
        
        igu.limpiarCampos();
        igu.desactivarEdicion();
        igu.activarTabla();
        igu.activarToolbar();
        
    }
    
    public void cancelar(){
    
        //igu.dispose();
        igu.limpiarCampos();
        igu.desactivarEdicion();
        igu.activarToolbar();
                
    }
    
    public void limpiarCampos(){
    
        igu.limpiarCampos();
    
    }
    
    
    public void primero(){
    
        igu.mostrarPrimero();
    
    }
    
    public void anterior(){
        
        igu.mostrarAnterior();
    
    }
    
    public void siguiente(){
        
        igu.mostrarSiguiente();
        
    }
    
    public void ultimo(){
        
        igu.mostrarUltimo();
    
    }
    
    @Override
    public void mouseClicked(MouseEvent evento){
    
        igu.setCamposDeTexto();
    
    }
    
    
}



/* package controlador;

import exceptions.ProductIDNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.DAOProductoHashMap;
import modelo.IDAOProducto;
import modelo.Producto;
import vista.IGUProductos;
import vista.IUTextoProducto;

public class ControlIGUProductos implements ActionListener {
    
    private IGUProductos igu;
    private IDAOProducto productos = new DAOProductoHashMap();
    
    public ControlIGUProductos(IGUProductos igu) {
        this.igu = igu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        try {
            switch(comando) {
                case "Aceptar":
                    productos.agregarProducto(igu.getProducto(true));
                    IUTextoProducto texto = new IUTextoProducto(); 
                    igu.activarTabla();
                    break;

                case "Eliminar":
                    productos.borrarProducto(igu.getId());
                    IUTextoProducto texto1 = new IUTextoProducto(); 
                    break;    

                case "Cancelar":
                    igu.dispose();
                    break;

                case "Limpiar campos":
                    igu.limpiarCampos();
                    break;

                case "Nuevo":
                    nuevo();
                    break;

                case "Borrar":
                    borrar();
                    break;

                case "Modificar":
                    modificar();
                    break;

                case "Buscar":                   
                    buscar();
                    break;

                case "Editar":
                    editar();
                    break;
            }
        } catch (ProductIDNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void nuevo() {
        igu.desactivarID();
        igu.activarCampos();
        igu.cambiarAccion("Aceptar");
    }
    
    public void borrar() {
        igu.activarID();
        igu.desactivarCampos();
        igu.cambiarAccion("Eliminar");
    }
    
    public void editar() {
        igu.activarID();
        igu.desactivarCampos();
        igu.cambiarAccion("Buscar");   
    }
    
    public void buscar() throws ProductIDNotFoundException {
        Producto producto = productos.getProductoById(igu.getId());
        igu.setProducto(producto);
        igu.cambiarAccion("Modificar");
        igu.activarCampos();
        igu.desactivarID();
    }
    
    public void modificar() throws ProductIDNotFoundException {
        productos.modificarProducto(igu.getId(), igu.getProducto(false));
        IUTextoProducto texto1 = new IUTextoProducto(); 
    }
}

*/