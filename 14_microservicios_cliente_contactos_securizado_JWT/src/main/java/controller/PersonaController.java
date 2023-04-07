package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	
	String url = "http://localhost:9013";
	
	//Credenciales
	String user="admin";
	String pwd ="admin";
	String token;
	
	//Headers para todos
	HttpHeaders headers = new HttpHeaders();
	
	/*Metodo para el Loggin
	 * */
	@PostConstruct()
	public void autenticar() {
		token = template.postForObject
				(url + "/login?user=" + user + "&pwd="+pwd, null, String.class);
		headers.add("Authorization", "Bearer " + token);
		System.out.println(token);
	}
	
	@GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(
			@PathVariable("nombre") String nombre,
			@PathVariable("email") String email,
			@PathVariable("edad") int edad
			){
		//Se crea el objeto a registrar
		Persona persona =  new Persona(nombre, email, edad);
		
		//Se hace una inserción POST con el RestTemplate
		//ANTES -->template.postForLocation(urlBase+"/contactos" , persona);
		template.exchange(url+"/contactos", HttpMethod.POST, new HttpEntity<Persona>(persona, headers), String.class);
		
		//En la segunda llamada se recibe al array con las personas existentes
		//ANTES --->Persona[] arrPers = template.getForObject(url+"/contactos", Persona[].class);
		Persona[] personas = template.exchange(url+"/contactos", HttpMethod.GET, new HttpEntity<>(headers), Persona[].class).getBody();
		//NOTA: El método RestTemplate.exchange() devuelve un ResponseEntity y para obtener su cuerpo se usa getBody();
		
		return Arrays.asList(personas);
	}
	
	@GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarPorEdades
		(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		//ANTES -->Persona[] personas = template.getForObject(url+"/contactos", Persona[].class);
		Persona[] personas = template.exchange(url+"/contactos", HttpMethod.GET, new HttpEntity<>(headers), Persona[].class).getBody();
		return Arrays.stream(personas)
				.filter( p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
				.collect(Collectors.toList());
	}
	
}