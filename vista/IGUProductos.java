package vista;

import controlador.ControlIGUProductos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.DAOProductoArrayList;

public class IGUProductos extends JInternalFrame{
    
    private JLabel[] etiquetas = {
    
        new JLabel("ID: "),
        new JLabel ("Nombre: "),
        new JLabel ("Ubicacion"),
        new JLabel ("Precio: "),
        new JLabel ("Costo: "),
        new JLabel ("Descuento: "),
        new JLabel ("Categoría: "),
        new JLabel ("Proveedor"),
        new JLabel ("Stock mínimo: "),
        new JLabel ("Stock máximo: "),
        new JLabel ("Existencias: ")
            
    };
    
    private JTextField[] campos = {
    
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10),
        new JTextField(10)

    };
    
    private BotonRedondeado btnAceptar = new BotonRedondeado("Aceptar");
            
    
    private JTable tabla;
    DefaultTableModel modeloTabla;
    
    private DAOProductoArrayList modelo = new DAOProductoArrayList();
    
    //Definir control apra esta ventana.
    private ControlIGUProductos controlProductos = new ControlIGUProductos(this);
    
    //Construir ventana en el constructor.
    public IGUProductos(){
        
        //redimensionable
        super("Catálogo de productos", true, true, true, true);
        setSize(690, 590);
        
        setLayout(null);
        
        add(getPanelEdicion(), BorderLayout.NORTH);
        
        add(getToolBar(), BorderLayout.CENTER);
        
        add(getPanelTabla(), BorderLayout.SOUTH);
        
        desactivarID();
        
        activarCampos();
        
                
    }
    
    public JPanel getPanelDatos(){
        
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(11, 2));
        //Agregar componentes.
        
        for(int pos = 0; pos < etiquetas.length; pos++){
            
            panelDatos.add(etiquetas[pos]);
            panelDatos.add(campos[pos]);
            
        }
        
        return panelDatos;
        
    }
    
    public JPanel getPanelBotones(){
        
        JPanel panelBotones = new JPanel();
        Box cajaBotones = Box.createVerticalBox();
        try {
        
            BotonRedondeado btnCancelar = btnAceptar.clone();
            btnCancelar.setText("Cancelar");
            
                
            BotonRedondeado btnLimpiar = btnAceptar.clone();
            btnLimpiar.setText("Limpiar campos");
        
            cajaBotones.add(Box.createVerticalStrut(15));
            cajaBotones.add(btnAceptar);
            cajaBotones.add(Box.createVerticalStrut(10));
            cajaBotones.add(btnCancelar);
            cajaBotones.add(Box.createVerticalStrut(10));
            cajaBotones.add(btnLimpiar);



            panelBotones.setBorder(new LineBorder(Color.BLUE));
            panelBotones.add(cajaBotones);

            btnAceptar.addActionListener(controlProductos);
            btnCancelar.addActionListener(controlProductos);
            btnLimpiar.addActionListener(controlProductos);
        
        }catch( CloneNotSupportedException exClone){
        
        }
        
        return panelBotones;
        
        
    }
    
    public JPanel getPanelEdicion(){
        
        JPanel panelEdicion = new JPanel();
        panelEdicion.setBorder(new TitledBorder(null, "Edición producto", 
            TitledBorder.CENTER, TitledBorder.TOP, null, null));
        
        panelEdicion.setLayout(new BorderLayout() );
        panelEdicion.add(getPanelDatos(), BorderLayout.CENTER);
        panelEdicion.add(getPanelBotones(), BorderLayout.EAST);
        
        panelEdicion.setBounds(0, 0, 475, 200);
        
        
        return panelEdicion;
        
    }
    
    public void cambiarAccion(String texto){
        
        btnAceptar.setText(texto);
        btnAceptar.setActionCommand(texto);
    
    }
    
    public int getId(){
    
        return Integer.parseInt(campos[0].getText());
    }
    
    
    public Producto getProducto(boolean cambiarId){


        int id;
        if(cambiarId ){
        
            id = 1;
            
        }else{
            
            id = Integer.parseInt(campos[0].getText()) ;
        }
        return new Producto(id,
                            campos[1].getText(),
                            campos[2].getText(),
                            Double.parseDouble(campos[3].getText()),
                            Double.parseDouble(campos[4].getText()),
                            Double.parseDouble(campos[5].getText()),
                            campos[6].getText(),
                            Integer.parseInt(campos[7].getText()),
                            Integer.parseInt(campos[8].getText()),
                            Integer.parseInt(campos[9].getText()),
                            Integer.parseInt(campos[10].getText())
        );
        
    }
    
    public void limpiarCampos(){
        
        
        for(JTextField campo : campos){
            
            campo.setText("");
    
        }
        
    }
   
    public JToolBar getToolBar(){
        
        JToolBar toolBar = new JToolBar();
        JButton btNuevo = new JButton(), btBorrar = new JButton("-"), btEditar = new JButton("E"); 
        
        btNuevo.addActionListener(controlProductos);
        btBorrar.addActionListener(controlProductos);
        btEditar.addActionListener(controlProductos);
        
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconos/plus.png")); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back

        
        btNuevo.setIcon(imageIcon);
        
        btNuevo.setActionCommand("Nuevo");
        btBorrar.setActionCommand("Borrar");
        btEditar.setActionCommand("Editar");
        
        toolBar.add(btNuevo);
        toolBar.add(btBorrar);
        toolBar.add(btEditar);
        
        toolBar.setBounds(3, 190, 470, 55);
        
        return toolBar;
        
    }
    
    public void activarID(){
        
        campos[0].setEnabled(true);
        
    }
    
    public void activarCampos(){
        
        for(int campo = 1; campo < campos.length ; campo++){
            
            campos[campo].setEnabled(true);
            
        }
    }
    
    public void desactivarID(){
        
        campos[0].setEnabled(false);
    }
    
    public void desactivarCampos(){
        
        for(int campo = 1; campo < campos.length ; campo++ ){
        
        campos[campo].setEnabled(false);
        }
        
    }
    
    public void setProducto(Producto producto){
        
        campos[1].setText(producto.getNombre());
        campos[2].setText(producto.getUbicacion());
        campos[3].setText(String.valueOf(producto.getPrecio()));
        campos[4].setText(String.valueOf(producto.getCosto()));
        campos[5].setText(String.valueOf(producto.getDescuento()));
        campos[6].setText(producto.getCategoria());
        campos[7].setText(String.valueOf(producto.getProveedor()));
        campos[8].setText(String.valueOf(producto.getStockMin()));
        campos[9].setText(String.valueOf(producto.getStockMax()));
        campos[10].setText(String.valueOf(producto.getExistencias()));
    }
    
    public JPanel getPanelTabla(){
        
        JPanel panelTabla = new JPanel();
        
        panelTabla.setBorder(new TitledBorder(null, "Lista de productos", 
                TitledBorder.CENTER, TitledBorder.TOP, null, null) );
        
        tabla = new JTable();
        
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabla);
        panelTabla.add(scroll);
        
        setTablaProductos();
        
        panelTabla.setBounds(5, 15, 470, 85);
        
        return panelTabla;
        
    }
    
    public void setTablaProductos(){
        
        String [] titulos = {"ID", "Nombre", "Ubicación", "Precio", "Costo", 
            "Descuento", "Categpría", "Proveedor", "Stock mínimo", "Stock máximo",
            "Existencias"};
        
        modeloTabla = new DefaultTableModel(){
                
                @Override
                public boolean isCellEditable(int renglon, int columna){
                    
                    return false;
                    
                }
            
            };
        
        //Mover esto eventualmente.
        Object[][] datos = modelo.getAllProductos();
        modeloTabla.setDataVector(datos, titulos);
        
        tabla.setModel(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }
    
    public void activarTabla(){
                
        modeloTabla.fireTableDataChanged();
        
    }
    
}


/*

package vista;

import controlador.ControlIGUProductos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.DAOProductoArrayList;

public class IGUProductos extends JInternalFrame {

    private JTextField[] campos;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnBuscar, btnCancelar, btnListar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private DAOProductoArrayList dao;
    private ControlIGUProductos control;

    public IGUProductos() {
        super("Gestión de Productos", true, true, true, true);
        setSize(800, 600);
        setLayout(new BorderLayout());

        dao = new DAOProductoArrayList();
        control = new ControlIGUProductos(this, dao);

        // Configuración de componentes de entrada
        JPanel panelCampos = new JPanel(new GridLayout(11, 2, 5, 5));
        String[] etiquetas = {"ID", "Nombre", "Ubicación", "Precio", "Costo", 
                              "Descuento", "Categoría", "Proveedor", "Stock Min", 
                              "Stock Max", "Existencias"};
        
        campos = new JTextField[etiquetas.length];
        
        for (int i = 0; i < etiquetas.length; i++) {
            JLabel label = new JLabel(etiquetas[i]);
            campos[i] = new JTextField(20);
            panelCampos.add(label);
            panelCampos.add(campos[i]);
        }
        
        panelCampos.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Datos del Producto"));
        add(panelCampos, BorderLayout.WEST);

        // Configuración de botones
        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 5, 5));
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnCancelar = new JButton("Cancelar");
        btnListar = new JButton("Listar");

        btnAgregar.addActionListener(control);
        btnActualizar.addActionListener(control);
        btnEliminar.addActionListener(control);
        btnBuscar.addActionListener(control);
        btnCancelar.addActionListener(control);
        btnListar.addActionListener(control);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnListar);

        add(panelBotones, BorderLayout.EAST);

        // Configuración de la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Ubicación", "Precio", "Costo", 
                                                        "Descuento", "Categoría", "Proveedor", "Stock Min", 
                                                        "Stock Max", "Existencias"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);
        panelTabla.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Lista de Productos"));
        
        add(panelTabla, BorderLayout.CENTER);
    }

    public Producto getProducto(boolean cambiarId) {
        int id = cambiarId ? 1 : Integer.parseInt(campos[0].getText());

        return new Producto.ProductoBuilder()
                .setId(id)
                .setNombre(campos[1].getText())
                .setUbicacion(campos[2].getText())
                .setPrecio(Double.parseDouble(campos[3].getText()))
                .setCosto(Double.parseDouble(campos[4].getText()))
                .setDescuento(Double.parseDouble(campos[5].getText()))
                .setCategoria(campos[6].getText())
                .setProveedor(Integer.parseInt(campos[7].getText()))
                .setStockMin(Integer.parseInt(campos[8].getText()))
                .setStockMax(Integer.parseInt(campos[9].getText()))
                .setExistencias(Integer.parseInt(campos[10].getText()))
                .build();
    }

    public void mostrarProducto(Producto producto) {
        
        campos[0].setText(String.valueOf(producto.getId()));
        campos[1].setText(producto.getNombre());
        campos[2].setText(producto.getUbicacion());
        campos[3].setText(String.valueOf(producto.getPrecio()));
        campos[4].setText(String.valueOf(producto.getCosto()));
        campos[5].setText(String.valueOf(producto.getDescuento()));
        campos[6].setText(producto.getCategoria());
        campos[7].setText(String.valueOf(producto.getProveedor()));
        campos[8].setText(String.valueOf(producto.getStockMin()));
        campos[9].setText(String.valueOf(producto.getStockMax()));
        campos[10].setText(String.valueOf(producto.getExistencias()));
        
    }

    public JButton getBtnAgregar(){ 
        
        return btnAgregar; 
    
    }
    
    public JButton getBtnActualizar(){ 
        
        return btnActualizar; 
    
    }
    
    public JButton getBtnEliminar(){ 
        
        return btnEliminar; 
    
    }
    
    public JButton getBtnBuscar(){ 
        
        return btnBuscar; 
    
    }
    
    public JButton getBtnCancelar(){ 
        
        return btnCancelar; 
    
    }
    
    public JButton getBtnListar(){ 
        
        return btnListar; 
    
    }
    
    public DefaultTableModel getModeloTabla(){ 
        
        return modeloTabla;
    
    }
    
    public JTable getTabla(){ 
        
        return tabla;
    
    }
}
