package spring_boot.fundamentos;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VariablesURLController {
	
	/* QUERY STRING 
	 * Parámetros que se envían dentro de la URL en forma de parejas "nombre=valor",
	 * separados de la dirección por una ?, en donde en el RestController son 
	 * mapeados mediante la anotación @RequestParam.
	 * 
	 * Dentro de los parámetros del método, son mapeados en la @notacion de la 
	 * forma: @RequestParam("name_of_var") Type varName
	 * 
	 * NOTA: No requiere el mapeo de los valores de la URL, dentro de la propiedad 
	 * "value" de la @notacion (GET, POST, UPDATE, DELETE, PATCH)
	 */
	@GetMapping(value="serv2", produces = MediaType.TEXT_PLAIN_VALUE)
	public String usandoQueryString(@RequestParam("v1") String name, 
			@RequestParam("v2") int valor) {
		
		//http://localhost:9090/servicio/serv2?v1=Chris&v2=26
		return "Probando RequestParam >>> " + name + " - " + valor;
	}
	
	
	/* PATH VARIABLE
	 * Recoger las variables de la URL al RESTController por medio de @PathVariable.
	 * 
	 * PathVariable: Parámetros que se envían dentro de la URL, separados por / 
	 * por cada variable.
	 * 
	 * En el RestController, dentro de las @notaciones 
	 * (GET, POST, UPDATE, DELETE, PATCH) son mapeados cada variable dentro de { } 
	 * y separados por una / cada variable.
	 * 
	 * Dentro de los parámetros del método, son mapeados en la @notacion de la 
	 * forma: @PathVariable("name_of_var") Type varName
	 */
	@GetMapping(value="serv3/{name}/{valor}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String usandoPathVariable(@PathVariable("name") String name,
			@PathVariable("valor") int valor) {
		
		//http://localhost:9090/servicio/serv3/Chris/26
		return "Probando PathVariable >>> " + name + " - " + valor;
	}
}
