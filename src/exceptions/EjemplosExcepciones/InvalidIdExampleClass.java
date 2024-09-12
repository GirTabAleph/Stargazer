//Esta es una clase que muestra cómo
//se debería usar la excepción personalizada
//para IDs.
package exceptions.EjemplosExcepciones;


import java.util.Scanner;
import exceptions.InvalidIDException;

public class InvalidIdExampleClass{

	//Método que lanza la excepción.
	public static void validarId(int id) throws InvalidIDException{

		//Que yo sepa no podemos trabajar ID 0.
		if(id <= 0){

			throw new InvalidIDException("Error. La ID no puede ser cero o negativa.");

		} //Fin if.

	} //Fin método validarId.

	//Método main de esta clase.
	public static void main(String args[]){

		Scanner entrada = new Scanner(System.in);
		boolean error = true;

		do{

			//Manejo de la excepción.
			try{
			
				System.out.print("Ingrese un ID: ");
				int id = entrada.nextInt();
				validarId(id);
				System.out.print("ID correcto. Continuando.");
				error = false;

			} catch(InvalidIDException ex){

				System.out.println(ex.getMessage());

			}

		} while(error);

	} //Fin método main.

} //Fin de la clase.
