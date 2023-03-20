package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/* EXCEPCION PERSONALIZADA
 * 
 * > Se pueden hacer una clase de excepciones heredando de la
 * clase Exception
 * 
 * > El ResponseStatus puede ir en clase (pondría el código de 
 * excepción implicítamente para toda la clase) o en método 
 * (para personalizar por método)
 * 
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MyExceptionPersonalized extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	//El msj de error ira
	public MyExceptionPersonalized(String message) {
		super(message);
	}



	//@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<String> manejaError(){
		return null;
	}
}
