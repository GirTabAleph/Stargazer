/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Diego Ortega
 */

public class GestionVendedor {

   private ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

   private IGUVendedor interfaz = new IGUVendedor();

   public void ejecutarControlador(){

      //variables auxiliares 

      String id;
      int opcion;
      Vendedor VendedorNuevo;

      //Controlador del menu principal
      do{
         opcion = interfaz.menuVendedor;

         switch (opcion) {

            case 1: //Agregar vendedor (CREATE)

               agregarVendedor();
            
               break;

            case 2: //Eliminar Vendedor (DELETE)

                  eliminarVendedor();

               break;

            case 3; //Buscar Vendedor (READ)

                  buscarVendedor(id);

               break;

            case 4: //Listar Vendedor (READ)

                  listarVendedor();

            case 0://Salir

               break;
         
            default:

               interfaz.mensaje("opcion no valida");

               break;
         }
      }while
   }


   private void agregarVendedor() {

      VendedorNuevo = Vendedor.leerDatosVendedor();
      Vendedor.agregarVendedor(VendedorNuevo);

   }
   

   private void eliminarVendedor() {

      id = interfaz.leerIdVendedor();

      if (Vendedor.buscarVendedor(id) == -1)

      {

         interfaz.mensaje("El id del vendedor no existe");

      } else {

         Vendedor.eliminarVendedor(id);
      }
   }

   private void buscarVendedor(String id) {

      int posicion = vendedores.buscarVendedor(id);

      if (posicion == -1) {

         interfaz.mensaje("El id del vendedor que ingresaste no existe");

      } else {

         Vendedor vendedorEscrito = vendedores.getVendedor(posicion);
         interfaz.mensaje(vendedorEscrito.imprimirDatos());
      }
   }

   private void listarVendedor() {
      
      interfaz.escribirVendedor(vendedores);
            
   }
}