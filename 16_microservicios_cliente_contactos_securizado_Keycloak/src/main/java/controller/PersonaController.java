package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import model.Persona;
import model.ResultAuth;

@RestController
public class PersonaController {

	@Autowired
	RestTemplate template;
	
	String urlService = "http://localhost:9015";
	String urlKeycloak = "http://localhost:8000/realms/ContactosRealm/protocol/openid-connect/token";
	
	//Constantes de conexion de Keycloak
	final String USERNAME 	= "admin";
	final String PASSWORD 	= "admin";
	final String CLIENT_ID	= "login";
	final String GRANT_TYPE	= "password";
	
	//Headers para todos
	HttpHeaders headersService = new HttpHeaders();
	
	/*Metodo para el Loggin
	 * */
	@PostConstruct()
	public void autenticar() {
		//Headers personales para el Login
		HttpHeaders headersKeycloak = new HttpHeaders();
		
		//cabecera con tipo de contenido del cuerpo
		headersKeycloak.add("Content-type", "application/x-www-form-urlencoded");
		
		//parametros que enviamos en el cuerpo de la llamada a Keycloak
		MultiValueMap<String, String> authData = new LinkedMultiValueMap<>();
		authData.add("client_id", CLIENT_ID);		
		authData.add("username", USERNAME);		
		authData.add("password", PASSWORD);		
		authData.add("grant_type", GRANT_TYPE);		
		
		//Peticion al Login y obtencion de la respuesta JWT
		ResponseEntity<ResultAuth> response = template.exchange
			(
				urlKeycloak,
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String, String>>
					(authData, headersKeycloak), 
				ResultAuth.class
			);
		
		//Se agrega el JWT obtenido al Header General de las Peticiones
		headersService.add("Authorization", "Bearer " + response.getBody().getAccess_token());
		System.out.println( response.getBody().getAccess_token() );
	}
	
	@GetMapping("/renew")
	public String renovarToken() {
		this.headersService = null;
		autenticar();
		System.out.println(headersService.getOrEmpty("Authorization"));
		return headersService.getOrEmpty("Authorization").toString();
	}
	
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
			//ANTES -->template.postForLocation(urlBase+"/contactos" , persona);
			template.exchange(urlService+"/contactos", HttpMethod.POST, new HttpEntity<Persona>(persona, headersService), String.class);
		}
		//Agregue estos Catch para debuggeo
		catch(HttpClientErrorException http) {
			System.out.println("\n\n\n ~~~ === HttpClientErrorException === ~~~");
			System.out.println("Error:\n\n\n" + http.getMessage());
			System.out.println("\n\n\n\nCause:\n\n\n" + http.getCause());
		}catch(Error e) {
			System.out.println("\n\n\n ~~~ === Error (Type Runtime Exception)=== ~~~");
			System.out.println("Error:\n\n\n" + e.getMessage());
			System.out.println("\n\n\n\nCause:\n\n\n" + e.getCause());
		}catch(Throwable t){
			System.out.println("\\n\\n\\n ~~~ === Type of Error && Exception exceptions === ~~~");
			System.out.println("Error:\n\n\n" + t.getMessage());
			System.out.println("\n\n\n\nCause:\n\n\n" + t.getCause());
		}
		
		
		//En la segunda llamada se recibe al array con las personas existentes
		//ANTES --->Persona[] arrPers = template.getForObject(url+"/contactos", Persona[].class);
		Persona[] personas = template.exchange(urlService+"/contactos", HttpMethod.GET, new HttpEntity<>(headersService), Persona[].class).getBody();
		//NOTA: El método RestTemplate.exchange() devuelve un ResponseEntity y para obtener su cuerpo se usa getBody();
		
		
		if (personas == null) {
			return new ArrayList<Persona>();
		}
		
		return Arrays.asList(personas);
	}
	
	@GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarPorEdades
		(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		//ANTES -->Persona[] personas = template.getForObject(url+"/contactos", Persona[].class);
		Persona[] personas = template.exchange(urlService+"/contactos", HttpMethod.GET, new HttpEntity<>(headersService), Persona[].class).getBody();
		return Arrays.stream(personas)
				.filter( p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
				.collect(Collectors.toList());
	}
	
}