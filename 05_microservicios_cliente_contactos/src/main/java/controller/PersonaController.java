package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@RestController
public class PersonaController {

	@Autowired
	RestTemplate template;
	
	String urlBase = "http://localhost:9004";

	@GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(
			@PathVariable("nombre") String nombre,
			@PathVariable("email") String email,
			@PathVariable("edad") int edad
			){
		//Se crea el objeto a registrar
		Persona persona =  new Persona(nombre, email, edad);
		
		//Se hace una inserción POST con el RestTemplate
		template.postForLocation(urlBase+"/contactos" , persona);
		
		//En la segunda llamada se recibe al array con las personas existentes
		Persona[] arrPers = template.getForObject(urlBase+"/contactos", Persona[].class);
		
		return Arrays.asList(arrPers);
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