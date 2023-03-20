package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@RestController
public class PersonaController {

	@Autowired
	RestTemplate template;
	
	String urlBase = "http://localhost:9004";

	@GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
	//public List<Persona> altaPersona(
	public ResponseEntity<List<Persona>> altaPersona(
			@PathVariable("nombre") String nombre,
			@PathVariable("email") String email,
			@PathVariable("edad") int edad
			){
		//Se crea el objeto a registrar
		Persona persona =  new Persona(nombre, email, edad);
		
		/**
		 * Esta metido en un try-catch por si al ejecutar el método
		 * "postForLocation()" obtuviera un código diferente al 200,
		 * provocando así que lanzé la excepcipon 
		 * (HttpStatusCodeException)
		 * 
		 * ¿Porque no usar "posForEntity()" para recuperar el error?
		 * Porque no se va a recibir nada de información. Para ello, 
		 * utilizamos el objeto de HttpStatusCodeException que nos 
		 * brindará herramientas como el Codigo de Estatus y el 
		 * cuerpo de la respuesta (del Error) 
		 * 
		 */
		try {
			//Se hace una inserción POST con el RestTemplate
			template.postForLocation(urlBase+"/contactos" , persona);
			
			//En la segunda llamada se recibe al array con las personas existentes
			Persona[] arrPers = template.getForObject(urlBase+"/contactos", Persona[].class);
			
			//return Arrays.asList(arrPers);
			return new ResponseEntity<List<Persona>>(Arrays.asList(arrPers), HttpStatus.OK);
		} catch (HttpStatusCodeException e) {
			/* si hubo error en la llamada al microservicio, enviamos a nuestro cliente final
			 * una cabecera con el mensaje de error, una lista vacía de personas en el cuerpo
			 * y el código de estado enviado desde el microservicio
			 */	
			e.printStackTrace();
			
			//Clase empleada para los headers del ResponseEntity
			HttpHeaders headers =  new HttpHeaders();
			
			//se agregan métodos así
			headers.add("error", e.getMessage());
			headers.add("Nombre_Error", "Cuerpo_Error");
			
			//Se devuelve un array vacío con el error en las headers
			return new ResponseEntity<List<Persona>>(new ArrayList<Persona>(), headers, e.getStatusCode());
		}
	}
	
	@GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarPorEdades
		(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		Persona[] personas = template.getForObject(urlBase+"/contactos", Persona[].class);
		return Arrays.stream(personas)
				.filter( p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
				.collect(Collectors.toList());
	}
	
}