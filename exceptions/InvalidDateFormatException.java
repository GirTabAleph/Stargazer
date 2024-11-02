//Clase de excepción para validar las fechas.
//La trabajé como SimpleDateFormat.
//Ver uso de esta excepción en su clase de ejemplo.
package exceptions;


public class InvalidDateFormatException extends Exception{


	//Constructor vacío.
	public InvalidDateFormatException(){

		super("Error, el formato de fecha no es válido. Ingrese "
				+ "una fecha correcta en formato dd-MMM-yyyy");

	}

	//Constructor que permite mensajes personalizados.
	public InvalidDateFormatException(String mensaje){

		super(mensaje);

	}

}
