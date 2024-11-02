package controlador;

public class GestionVentas {
    

    
// ATRIBUTOS


	private ArrayList<Venta> ventas = new ArrayList<>(); // Lista de ventas

    private IGUVentas interfaz = new IGUVentas(); // VISTA

    // METODO PRINCIPAL CLASE VENTA

    public void ejecutarControlador() {

        // VARIABLES AUXILIARES

        int opcion;

        // CONTROLADOR DE MENU PRINCIPAL

        do {
            
            opcion = interfaz.menuVentas();

            // SWITCH MENU

            switch (opcion) {

                case 1: // AGREGAR (CREATE)

                    Venta nuevaVenta = Venta.crearVenta(obtenerProductos(), obtenerClientes(), obtenerVendedores());
                    ventas.add(nuevaVenta);
                    interfaz.mensaje("Venta registrada con éxito. Total: " + nuevaVenta.getCostoTotal());
                    break;

                case 2: // ELIMINAR (DELETE)

                    int idEliminar = interfaz.leerIdVenta();
                    Venta ventaAEliminar = buscarVentaPorId(idEliminar);
                    
                    if (ventaAEliminar != null) {

                        ventas.remove(ventaAEliminar);
                        interfaz.mensaje("Venta eliminada con éxito.");

                    } else {

                        interfaz.mensaje("El id que ingresaste no existe.");

                    }
                    break;
                
                case 3: // BUSCAR (READ)

                    int idBuscar = interfaz.leerIdVenta();
                    Venta ventaBuscada = buscarVentaPorId(idBuscar);

                    if (ventaBuscada != null) {

                        interfaz.mensaje(ventaBuscada.imprimirDatos());

                    } else {

                        interfaz.mensaje("El id que ingresaste no existe.");

                    }

                    break;

                case 4: // LISTAR (READ)

                    interfaz.escribirVentas(ventas);
                    break;

                case 0: // SALIR

                    break;

                default:

                    interfaz.mensaje("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }

    // Método auxiliar para buscar una venta por su ID

    private Venta buscarVentaPorId(int id) {
        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                return venta;
            }
        }
        return null;
    }