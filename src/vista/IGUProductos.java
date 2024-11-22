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
import modelo.IDAOProducto;
import modelo.bd.DAOProductoBD;

public class IGUProductos extends VentanaInterna{
    
    private static IGUProductos singleton;
    
    private JToolBar toolbar;
    
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
    
    private Object[][] matrizProductos;
    
    private String[] titulosTabla = {
            
            "ID", 
            "Nombre", 
            "Ubicación", 
            "Precio", 
            "Costo",
            "Descuento", 
            "Categoría", 
            "ID proveedor", 
            "Stock mínimo", 
            "Stock máximo", 
            "Existencias"
        
        };
    
    private JButton btnAceptar, btnCancelar, btnLimpiar;
    
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    
    private IDAOProducto modelo; //CAMBIAR EL DAO LPM.
    
    //Definir control apra esta ventana.
    private ControlIGUProductos controlProductos;
    
    //Construir ventana en el constructor.
    private IGUProductos(){
        
        //redimensionable
        super("Catálogo de productos", true, true, true, true);
        setSize(690, 590);
        
        initComponents();
        
        Box panel = Box.createVerticalBox();
        
        panel.add(getPanelEdicion());
        
        panel.add(toolbar);
        
        panel.add(getPanelTabla());
        
        add(panel, BorderLayout.CENTER);
        
        desactivarEdicion();
                
    }
    
    public void desactivarEdicion(){
        
        desactivarID();
        
        desactivarCampos();
        
        desactivarBotones();
        
        activarToolbar();
        
    }
    
    public void initComponents(){
       
         //Definir control para esta ventana.
        controlProductos = new ControlIGUProductos(this);
        
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnLimpiar = new JButton("Limpiar campos");
        
        toolbar = getToolBar();
        
        tabla = new JTable();
        
        //Escucha para eventos de ratón.
        tabla.addMouseListener(controlProductos);
        
        modeloTabla = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int renglon, int columna){
                
                return false;
                
            }
        };

        modelo = new DAOProductoBD();
        
        //Esta matriz son propiamente los productos.
        matrizProductos = modelo.getAllProductos();
        
        //Para las columnas de la tabla.
        /*
        int id, String nombre, String ubicacion, double precio, double costo,
                    double descuento, String categoria, int proveedor, int stockMin,
                    int stockMax, int existencias) {
        
        */
        
        
        modeloTabla.setDataVector(matrizProductos, titulosTabla);
        
       
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
        
        return panelBotones;
        
    }
    
    public JPanel getPanelEdicion(){
        
        JPanel panelEdicion = new JPanel();
        panelEdicion.setBorder(new TitledBorder(null, "Edición producto", 
            TitledBorder.CENTER, TitledBorder.TOP, null, null));
        
        panelEdicion.setLayout(new BorderLayout() );
        panelEdicion.add(getPanelDatos(), BorderLayout.CENTER);
        panelEdicion.add(getPanelBotones(), BorderLayout.EAST);
        
        return panelEdicion;
        
    }
    
    public void cambiarAccion(String texto){
        
        btnAceptar.setText(texto);
        btnAceptar.setActionCommand(texto);
    
    }
    
    public int getId(){
            
        
        System.out.println( campos[0].getText().equals("") );
        if(campos[0].getText().equals("")){
        
            return 0;
        
        } else {
        
            return Integer.parseInt(campos[0].getText());
        
        }
    
    }
    
    
    public Producto getProducto(boolean cambiarId){
        
        /*
        int id, String nombre, String ubicacion, double precio, double costo,
                    double descuento, String categoria, int proveedor, int stockMin,
                    int stockMax, int existencias
        */
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
    
    public Producto getProducto(){
        
        return new Producto(campos[1].getText(),
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
        JButton btNuevo = new JButton(), btBorrar = new JButton(), btEditar = new JButton(),
                btPrimero = new JButton(), btAnterior = new JButton(), btSiguiente = new JButton(), 
                btUltimo = new JButton(); 
        
        btNuevo.addActionListener(controlProductos);
        btBorrar.addActionListener(controlProductos);
        btEditar.addActionListener(controlProductos);
        btPrimero.addActionListener(controlProductos);
        btAnterior.addActionListener(controlProductos);
        btSiguiente.addActionListener(controlProductos);
        btUltimo.addActionListener(controlProductos);
        
        
        /*
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconos/plus.png")); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        */
        
        btNuevo.setIcon(getIcono(new ImageIcon(getClass().getResource("/iconos/nuevo.png")) ));
        btBorrar.setIcon(getIcono(new ImageIcon(getClass().getResource("/iconos/borrar.png")) ));
        btEditar.setIcon(getIcono(new ImageIcon(getClass().getResource("/iconos/editar.png")) ));
        btPrimero.setIcon(getIcono(new ImageIcon(getClass().getResource("/iconos/primero.png")) ));
        btAnterior.setIcon(getIcono(new ImageIcon(getClass().getResource("/iconos/anterior.png")) ));
        btSiguiente.setIcon(getIcono(new ImageIcon(getClass().getResource("/iconos/siguiente.png")) ));
        btUltimo.setIcon(getIcono(new ImageIcon(getClass().getResource("/iconos/ultimo.png")) ));
        
        btNuevo.setActionCommand("Nuevo");
        btBorrar.setActionCommand("Borrar");
        btEditar.setActionCommand("Editar");
        btAnterior.setActionCommand("Anterior");
        btSiguiente.setActionCommand("Siguiente");
        btPrimero.setActionCommand("Primero");
        btUltimo.setActionCommand("Ultimo");
        
        toolBar.add(btNuevo);
        toolBar.add(btBorrar);
        toolBar.add(btEditar);
        toolBar.add(btPrimero);
        toolBar.add(btAnterior);
        toolBar.add(btSiguiente);
        toolBar.add(btUltimo);
        
        
        
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
    
    /*
        int id, String nombre, String ubicacion, double precio, double costo,
                    double descuento, String categoria, int proveedor, int stockMin,
                    int stockMax, int existencias
        */
    
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

            //modeloTabla.fireTableDataChanged();
            matrizProductos = modelo.getAllProductos();
            modeloTabla.setDataVector(matrizProductos, titulosTabla);


        }
    
    public ImageIcon getIcono(ImageIcon imagen){
        
        int alto = 30;
        int ancho = 30;
        
        ImageIcon nuevo = 
                new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        
        return nuevo;
        
    }
    
    public static IGUProductos getInstance(){
        
        if(singleton == null){
            
            singleton = new IGUProductos();
            
        }
        
        return singleton;
        
    }
    
    public void mostrarPrimero(){
        
        if(tabla.getRowCount() > 0){
            
            tabla.changeSelection(0, 0, false, false);
            
        }
        
        setCamposDeTexto();
        
    }
    
    public void mostrarAnterior(){
        
        int renglon = tabla.getSelectedRow();
        
        if(renglon > 0){
            
            tabla.changeSelection(renglon - 1, 0, false, false);
            
        } else {
            
            tabla.changeSelection(tabla.getRowCount() - 1, 0, false, false);
            
        }
        
        setCamposDeTexto();
        
    }
    
    public void mostrarSiguiente(){
        
        int renglon = tabla.getSelectedRow();
        
        if(renglon < tabla.getRowCount() - 1){
            
            tabla.changeSelection(renglon + 1, 0, false, false);
            
        } else {
            
            //Ya estamos en el último, nos vamos al primero.
            tabla.changeSelection(0, 0, false, false);
            
        }
        
        setCamposDeTexto();
        
    }
    
    public void mostrarUltimo(){
        
        tabla.changeSelection(tabla.getRowCount() - 1, 0, false, false);
        setCamposDeTexto();
        
    }
    
    public void setCamposDeTexto(){
        
        int renglon = tabla.getSelectedRow();
        
        if(renglon >= 0 && tabla.getRowCount() != 0){
            
            campos[0].setText(tabla.getValueAt(renglon, 0).toString());
            campos[1].setText(tabla.getValueAt(renglon, 1).toString());
            campos[2].setText(tabla.getValueAt(renglon, 2).toString());
            campos[3].setText(tabla.getValueAt(renglon, 3).toString());
            campos[4].setText(tabla.getValueAt(renglon, 4).toString());
            campos[5].setText(tabla.getValueAt(renglon, 5).toString());
            campos[6].setText(tabla.getValueAt(renglon, 6).toString());
            campos[7].setText(tabla.getValueAt(renglon, 7).toString());
            campos[8].setText(tabla.getValueAt(renglon, 8).toString());
            campos[9].setText(tabla.getValueAt(renglon, 9).toString());
            campos[10].setText(tabla.getValueAt(renglon, 10).toString());
            
        }
        
    }
    
    public void activarBotones(){
        
        btnAceptar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnLimpiar.setEnabled(true);
        
    }
    
    public void desactivarBotones(){
        
        btnAceptar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnLimpiar.setEnabled(false);
        
    }
    
    public void activarToolbar(){
        
        JButton boton;
        
        for(int i = 0; i < toolbar.getComponentCount() ; i++){
            
            if(toolbar.getComponent(i) instanceof JButton){
                
                boton = (JButton)toolbar.getComponent(i);
                boton.setEnabled(true);
                
            }
            
        }
        
    }
    
    public void desactivarToolbar(){
        
        JButton boton;
        
        for(int i = 0; i < toolbar.getComponentCount() ; i++){
            
            if(toolbar.getComponent(i) instanceof JButton){
                
                boton = (JButton)toolbar.getComponent(i);
                boton.setEnabled(false);
                
            }
            
        }
        
    }
}
