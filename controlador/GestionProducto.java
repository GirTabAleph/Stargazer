package controlador;

public class GestionProducto {

// ATRIBUTOS

    private ArrayListProducto productos = new ArrayListProducto("db");

    private IGUProducto interfaz = new IGUProducto();


    public void ejecutarControlador() {

        // VARIABLES AUXILIARES

        String id;
        int opcion;
        Producto productoNuevo;

        // CONTROLADOR DE MENU PRINCIPAL

        do {

            opcion = interfaz.menuProducto();

            // SWITCH MENU

            switch (opcion) {

                case 1: // AGREGAR (CREATE)

                    productoNuevo = interfaz.leerDatosProducto();
                    Productos.agregarProducto(productoNuevo);

                    break;

                case 2: // ELIMINAR (DELETE)

                    id = interfaz.leerIdProducto();

                    if (Productos.buscarProducto(id) == -1) {

                        interfaz.mensaje("El id que ingresaste no existe");

                    } else {

                        Productos.eliminarProducto(id);

                    }

                    break;

                case 3: // BUSCAR (READ)

                    id = interfaz.leerIdProducto();

                    int posicion = Productos.buscarProducto(id);

                    if (posicion == -1) {

                        interfaz.mensaje("El id que ingresaste no existe");

                    } else {

                        Producto ProductoEscrito = Productos.getProducto(posicion);

                        interfaz.mensaje(ProductoEscrito.imprimirDatos());
                    }
 
                    break;

                case 4: // LISTAR (READ)

                    interfaz.escribirProductos(Productos);

                    break;

                case 0: // SALIR

                    break;

                default:

                    break;

            }

        } while (opcion != 0);

    }

}