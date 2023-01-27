package spring_boot.fundamentos;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

	/* PARAMETROS EN ANOTACIONES
	 * value|name = Indicamos la URL que vamos a asociar a un método
	 * produces = Indicamos el tipo de formato que vamos a generar 
	 * (con el método asociado a la URL)
	 */
	@GetMapping(value = "saludo", produces = MediaType.TEXT_PLAIN_VALUE)
	public String tipicoHolaMundo() {
		return "Hola Mundo, si ves esto es una todo salió bien";
	}
	
	/* ARCHIVOS DE CONFIGURACIÓN
	 * 
	 * Son archivos que proveen de configuraciones extra o 
	 * sobreescriben la configuración por defecto.
	 * 
	 * > Se definen las propiedades de configuración de la 
	 * aplicación en Spring Boot como parejas:: nombre=valor
	 * 
	 * >Se establecen mediante cualquiera estos tipos de 
	 * archivos:
	 * 	- application.properties
	 *  - application.yml
	 *  
	 * Se pueden encontrar más información sobre las propiedades más
	 * mportantes en: 
	 * https://docs.spring.io/spring-boot/docs/current/reference/html/appendix- application-properties.html
	 * 	
	 * Algunas de las más populares propiedades son:
	 * 
	 * server.port: Para modificar el puerto de escucha utilizado por el servidor embebido.
	 * spring.config.name: Sirve para poder usar otro archivo (en cualquier formato) para la configuración del microservicio.
	 * server.servlet.context-path: Para poner una (URL o) dirección raíz para todo el micro. Todas las URL estarán bajo este directorio raíz.
	 * spring.config.name: Sirve para cambiar el archivo de configuración por defecto. Para ello, basta con indicar el nombre del otro archivo en esta propiedad.
	 * 
	 * De este servicio esta en:
	 * localhost:9090/servicio/saludo
	 */
	
	/*
	 * VARIABLES EN URL
	 * Datos que se envian como parte de la URL, 
	 * incluyéndolos a continuación de la dirección del servicio
	 * 
	 * Teniendo la siguiente URL:
	 * 		http://servidor:8080/app/servicio/pl/p2
	 * 
	 * Donde:
	 *  /app 		== Context path
	 *  /servicio 	== Direccion del servicio
	 *  /p1/p2 		== Variables en la URL
	 */

	//http://localhost:9090/servicio/saludo/Chris/26
	@GetMapping(value = "saludo/{name}/{anios}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String saludar
		(@PathVariable(name = "name") String a,
		 @PathVariable("anios") int b) {
		return "Saludos a " + a + " con " + b + " años"; 
	}
}
