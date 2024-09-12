//Clase que se encarga del manejo de ID no válida.
package exceptions;

public class InvalidIDException extends Exception{
	
	//Constructor vacío.
	public InvalidIDException(){

		super("Error: el ID es inválido.");
	
	}

	//Este constructor recibe un mensaje personalizado, fuera de eso
	//es igual al vacío, hace lo mismo.
	public InvalidIDException(String mensajeError){

		super(mensajeError);

	}

}
