package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* CAPTURA DE EXCEPCIONES EN SPRING BOOT
 * 
 * > Se capturan en una clase independiente y Spring se encargá 
 * de relacionar las excepciones (provocadas) con esta clase.
 * 
 * > Las excepciones se capturan en una clase independiente 
 * anotada con @RestControllerAdvice
 * 
 */
@RestControllerAdvice
public class GestionExcepciones {

	/* 
	 * > Cada tipo de excepción es gestionada por un método
	 * @ExceptionHandler, que devuelve un ResponseEntity
	 * 
	 * NOTA: 
	 * Si se usarán las clases de excepcion personalizada en
	 * vez de las usuales, se deberán cambiar el valor en los 
	 * parámteros, tal como en @ExceptionHandler y 
	 * el parámetro del método
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> manejaError(Exception e){
		/* Se devuelve:
		 * > Param 1: el mensaje o el cuerpo de la Exception 
		 * > Param 2: el codigo del Estatus del error
		 */
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
