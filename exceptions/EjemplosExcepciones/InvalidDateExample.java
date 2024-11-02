//Clase que muestra cómo se usa la excepción de formato de fecha inválido.
package exceptions.EjemplosExcepciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import exceptions.InvalidDateFormatException;

public class InvalidDateExample{

	//Método que usa la excepción.
	public static void validarFecha(String date) throws InvalidDateFormatException{

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");


		//Asegurar queno se acepten fechas mafufas como el 32 de febrero.
		sdf.setLenient(false);

		//Bloque protegido try-catch
		try{
			//Si el formato no es, lanzamos ParseException.
			sdf.parse(date);
		
		} catch(ParseException ex){

			throw new InvalidDateFormatException();
		
		} //Fin try-catch

	} //Fin validarFecha.
	
	public static void main(String args[]){

		try{

			//Probar con fecha mafufa.
			validarFecha("44-Feb-2024");
			System.out.println("La fecha es válida.");

		} catch (InvalidDateFormatException ex){

			System.out.println( ex.getMessage() );

		} //Fin de try-catch
		
	} //Fin main.
	
} //Fin clase.
