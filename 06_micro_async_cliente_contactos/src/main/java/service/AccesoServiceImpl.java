package service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@Service
public class AccesoServiceImpl implements AccesoService {
	
	@Autowired
	RestTemplate template;
	
	String urlBase = "http://localhost:9004";
	
	/* @Async
	 * > Usado para que los métodos sean ejecutados en un
	 * hilo independiente
	 * 
	 * CompletableFuture
	 * > En los métodos, el tipo CompletableFuture permite 
	 * al cliente controlar la respuesta
	 */
	@Override
	@Async
	public CompletableFuture<List<Persona>> llamadaServicioAsync(Persona persona){
		//Se hace una inserción POST con el RestTemplate
		template.postForLocation(urlBase+"/contactos" , persona);

		//En la segunda llamada se recibe al array con las personas existentes
		Persona[] arrPers = template.getForObject(urlBase+"/contactos", Persona[].class);
		
		/* Se devuele un CompletableFuture que encapsula el objeto que vamos
		 * a devolver; esto se hace con el método estático completedFuture() 
		 * 
		 * Su tipo de devolución es el mismo del objeto que se le 
		 * proporciona en el parámetro
		 * */
		return CompletableFuture.completedFuture(Arrays.asList(arrPers));
	}
}
