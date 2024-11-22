package controlador;

import Vista.IGUInterfazPrincipal;

public class ControladorCentral {


// ATRIBUTOS

    private static IGUInterfazPrincipal interfaz = new IGUInterfazPrincipal ();
    private static GestionVentas controladorVentas = new GestionVentas();
    private static GestionProducto controladorProducto = new GestionProducto();

// FUNCION MAIN 

    public static void main(String[] args) {

        int opcion;

        do {

            opcion = interfaz.menuPrincipal();

            // SWITCH MENU

            switch (opcion) {

                case 1: // GESTIONAR VENTAS

                    controladorVentas.ejecutarControlador();
                    break;

                case 2: // GESTIONAR Cliente

                   
                    break;

                case 3: // GESTIONAR Vendedor

                   
                    break;

                case 4: // GESTIONAR PRODUCTOS

                    controladorProducto.ejecutarControlador();
                    break;

                case 0: // SALIR

                    break;

            }

        } while(opcion != 0);



    }

     
    
}