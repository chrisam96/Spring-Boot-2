package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@RestController
public class PersonaController {

	@Autowired
	RestTemplate template;
	
	String urlBase = "http://localhost:9009";

	@GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(
			@PathVariable("nombre") String nombre,
			@PathVariable("email") String email,
			@PathVariable("edad") int edad
			){
		//Se crea el objeto a registrar
		Persona persona =  new Persona(nombre, email, edad);
		try {
			//Se hace una inserción POST con el RestTemplate
			////template.postForLocation(urlBase+"/contactos" , persona);
			ResponseEntity<Void> respuesta = template.postForEntity(urlBase+"/contactos" , persona, Void.class);
			
			if (respuesta.getStatusCode().equals(HttpStatus.CONFLICT)) {
				System.out.println("no sé agrego al nuevo contacto");
				return null;
			}

			////MOVIDO DE LUGAR PARA EVITAR ERROR DE LOGICA (en base al curso del video)
			//En la segunda llamada se recibe al array con las personas existentes
			////Persona[] arrPers = template.getForObject(urlBase+"/contactos", Persona[].class);
			ResponseEntity<Persona[]> personas = template.getForEntity(urlBase+"/contactos", Persona[].class);
			
			//Recuperamos los headers para obtener el total de Personas
			HttpHeaders headers = personas.getHeaders();
			
			int total = Integer.parseInt(headers.get("total").get(0));
			
			//Si no encuentra nada, devuelve 0
			if (total == 0) {
				return null;
			}
			
			
			//return Arrays.asList(arrPers);
			return Arrays.asList(personas.getBody());
		}
		catch(HttpClientErrorException err) {
			err.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarPorEdades
		(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		//Persona[] personas = template.getForObject(urlBase+"/contactos", Persona[].class);
		ResponseEntity<Persona[]> personas = template.getForEntity(urlBase+"/contactos", Persona[].class);
		return Arrays.stream(personas.getBody())
				.filter( p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
				.collect(Collectors.toList());
	}
	
}