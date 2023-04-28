package inicio;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.Persona;

/* [[[Definición de la interfaz de OpenFeign]]]

Para que Spring implemente el cliente Feign, es necesario definir una interfaz 
con los métodos que debe exponer.

> Al generar la interfaz se debe de anotar con @FeignClien donde en "value" se debe 
indicar el identificador del servicio remoto (de Eureka Server). 

> Si se quiere acceder directamente en lugar de hacerlo por eureka, 
se utilizará el atributo URL.
 * 
 */
@FeignClient(value = "servicio-contactos")
public interface PersonaFeign {
	/* NOTA
	 * Estos metodos los tiene que implementar Spring. Internamente tendrá que 
	 * utilizar llamadas a tráves de RestTemplate. 
	 * 
	 * Por eso se tiene un cliente Eureka
	 * 
	 */
	
	/*En cada método de la interfaz se configura las peticiones que serán lanzadas
	 * desde los método, tal cual fuese un Controller.*/
	@GetMapping(value = "contactos", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Persona> getPersonas();
	
	@PostMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE)
	void altaPersona(@RequestBody Persona persona);
}
